package com.tobi.stock.services;

import com.tobi.stock.enums.TransactionType;
import com.tobi.stock.exceptions.NotEnoughStockSharesInPortfolio;
import com.tobi.stock.exceptions.PortfolioNotFoundException;
import com.tobi.stock.exceptions.StockNotInHandException;
import com.tobi.stock.models.Investor;
import com.tobi.stock.models.Portfolio;
import com.tobi.stock.models.Stock;
import com.tobi.stock.models.Transaction;
import com.tobi.stock.repositories.PortfolioRepository;
import com.tobi.stock.services.api.IEXQuote;
import com.tobi.stock.services.api.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 2:50 PM
 **/

@Service
public class PortfolioService extends BaseService<Portfolio, Long> {

    StockService stockService;
    PortfolioRepository portfolioRepository;
    TransactionService transactionService;
    QuoteService quoteService;

    @Autowired
    public void setPortfolioRepository(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
        setRepository(portfolioRepository);
    }

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public void createPortfolio(Investor investor) {
        Portfolio portfolio = new Portfolio();

        portfolio.setOwner(investor.getUserName());
        portfolio.setName("Default Portfolio");
        portfolio.setAvailableFunds(BigDecimal.valueOf(10000000));
        portfolio.setCreatedOn(new Date());
        portfolio.setUpdatedOn(new Date());

        save(portfolio);
    }

    /**
     * @param portfolioId
     * @return list of portfolio transactions
     */
    public List<Transaction> getTransactions(Long portfolioId) {
        return transactionService.findByPortfolioId(portfolioId);
    }

    /**
     *
     * @param portfolioId the portfolio Id
     * @return all the stocks that for the portfolio
     */
    public List<Stock> getStocks(Long portfolioId) {
        return stockService.findByPortfolioId( portfolioId);
    }

    public Portfolio getPortfolioByOwner(String owner) {
        Optional<Portfolio> optional = Optional.ofNullable(portfolioRepository.findByOwner(owner));
        if(!optional.isPresent()){
            throw new PortfolioNotFoundException("Selected Portfolio does not exist.");
        }
        Portfolio portfolio = optional.get();
        return portfolio;
    }

    /**
     *
     * @param owner owner of the portfolio
     * @return the stock that are still in hand
     */
    public List<Stock> getInHandStocks(String owner) {
        Portfolio portfolio = getPortfolioByOwner(owner);
        return stockService.findByInHandAndPortfolioId(true, portfolio.getId());
    }

    /**
     *
     * @param portfolioId the portfolio Id
     * @return the stock that are not still in hand
     */
    public List<Stock> getNotInStocks(Long portfolioId) {
        return stockService.findByInHandAndPortfolioId(false, portfolioId);
    }

    /**
     *
     * @param portfolioId the portfolio Id
     * @return distinct stock that are not still in hand
     */
    public List<Stock> findDistinctByInHandAndPortfolioId( Long portfolioId) {
        return stockService.findDistinctByInHandAndPortfolioId(true, portfolioId);
    }

    public Quote getQuoteInfo(String symbol) {
        return quoteService.getQuote(symbol);
    }

    /**
     *
     * @param stock share stock to sell
     * @param numberOfSharesToSell number of shares to sell
     * @param owner the owner of the portfolio
     * @return returns updated Portfolio
     */
    @Transactional
    public Portfolio sellStock(Stock stock, Long numberOfSharesToSell, String owner) {

        Portfolio portfolio = getPortfolioByOwner(owner);

        Stock inHandstock = stockService.findBySymbolAndPortfolioId(stock.getSymbol(), portfolio.getId());
        if (numberOfSharesToSell.compareTo(inHandstock.getNumShares()) > 0){
            throw new NotEnoughStockSharesInPortfolio("Cannot sell because number you don't have enough shares.");
        }
        if(!inHandstock.getInHand()) {
            throw new StockNotInHandException("Cannot sell because you don't ouwn the stock");
        }

        // Update the portfolio
        BigDecimal totalAmount = stock.getPrice().multiply(BigDecimal.valueOf(numberOfSharesToSell)).stripTrailingZeros();
        portfolio.setAvailableFunds(portfolio.getAvailableFunds().add(totalAmount));
        portfolio.setNumberOfShares(portfolio.getNumberOfShares() - numberOfSharesToSell);
        portfolio.setCurrentValue(calculateCurrentValue(portfolio.getOwner()));
        portfolio.setUpdatedOn(new Date());
        portfolio = portfolioRepository.save(portfolio);


        //Update the stock
        inHandstock.setSoldAt(stock.getPrice());
        inHandstock.setSoldAtOn(new Date());
        inHandstock.setNumShares(inHandstock.getNumShares() - numberOfSharesToSell);
        if (inHandstock.getNumShares() < 1){
            inHandstock.setInHand(false);
        }
        inHandstock.setPortfolio(portfolio);
        stockService.save(inHandstock);


        Transaction transaction = new Transaction();
        transaction.setStockSymbol(stock.getSymbol());
        transaction.setStockCompanyName(stock.getCompanyName());
        transaction.setNumberOfShares(numberOfSharesToSell);
        transaction.setUnitAmount(stock.getPrice());
        transaction.setAmountTotal(totalAmount);
        transaction.setTransactionType(TransactionType.C);
        transaction.setCreatedOn(new Date());
        transaction.setPortfolio(portfolio);
        transactionService.save(transaction);

        return getPortfolioByOwner(owner);
    }

    /**
     *
     * @param stock share stock to buy
     * @param numberOfSharesToBuy
     * @param owner the owner of the portfolio
     * @return returns updated Portfolio
     */
    @Transactional
    public Portfolio buyStock(Stock stock, Long numberOfSharesToBuy, String owner) {

        Portfolio portfolio = getPortfolioByOwner(owner);

        // Update the portfolio
        BigDecimal totalAmount = stock.getPrice().multiply(BigDecimal.valueOf(numberOfSharesToBuy)).stripTrailingZeros();
        portfolio.setAvailableFunds(portfolio.getAvailableFunds().add(totalAmount.negate()));
        portfolio.setNumberOfShares(portfolio.getNumberOfShares() + numberOfSharesToBuy);
        portfolio.setUpdatedOn(new Date());
        portfolio.setTotalSpent(portfolio.getTotalSpent().add(totalAmount));
        portfolio = portfolioRepository.save(portfolio);

        //Update the stock
        stock.setNumShares(numberOfSharesToBuy);
        stock.setInHand(true);
        stock.setPortfolio(portfolio);
        stockService.save(stock);

        Transaction transaction = new Transaction();
        transaction.setStockSymbol(stock.getSymbol());
        transaction.setStockCompanyName(stock.getCompanyName());
        transaction.setNumberOfShares(numberOfSharesToBuy);
        transaction.setUnitAmount(stock.getPrice());
        transaction.setAmountTotal(totalAmount);
        transaction.setTransactionType(TransactionType.D);
        transaction.setCreatedOn(new Date());
        transaction.setPortfolio(portfolio);
        transactionService.save(transaction);
        return getPortfolioByOwner(owner);
    }

    /**
     *
     * @param owner owner of the portfolio
     * @param from start date
     * @param to end date
     * @return
     */
    public List<Transaction> getTransactions(String owner, Date from, Date to) {
        Portfolio portfolio = getPortfolioByOwner(owner);
        return  transactionService.findByPortfolioIdAndCreatedOnBetween(portfolio.getId(), from, to);
    }

    private Map<String, Long> getStockNumberOfSharesMap(List<Stock> stocks) {
        Map<String, Long> stockSharesMap = new HashMap<>();
        stocks.forEach(stock -> {
            if(stockSharesMap.containsKey(stock.getSymbol()))
                stockSharesMap.merge(stock.getSymbol(), stock.getNumShares(), Long::sum);
            else
                stockSharesMap.put(stock.getSymbol().toLowerCase(), stock.getNumShares());
        });
        return stockSharesMap;

    }
    public BigDecimal calculateCurrentValue(String owner) {
        List<Stock> inHandStock = getInHandStocks(owner);

        List<String> inHandStockNames = inHandStock.stream().map(Stock::getSymbol).distinct().collect(Collectors.toList());
        Map<String, Quote> stringQuoteMap = quoteService.getQuote(inHandStockNames);
        Map<String, Long> stockSharesMap = getStockNumberOfSharesMap(inHandStock);
        BigDecimal cVal = BigDecimal.ZERO;

        for (String symbol : inHandStockNames) {
            Quote quote = stringQuoteMap.get(symbol.toLowerCase());
            Long numberOfShares = stockSharesMap.get(symbol.toLowerCase());
            BigDecimal totalAmount = quote.getLatestPrice().multiply(BigDecimal.valueOf(numberOfShares)).stripTrailingZeros();
            cVal = cVal.add(totalAmount);
        }

        return cVal;
    }
}
