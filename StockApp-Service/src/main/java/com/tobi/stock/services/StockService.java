package com.tobi.stock.services;

import com.tobi.stock.exceptions.StockInHandException;
import com.tobi.stock.models.Stock;
import com.tobi.stock.repositories.StockRepository;
import com.tobi.stock.services.api.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 1:30 AM
 **/

@Service
public class StockService extends BaseService<Stock, Long> {

    private StockRepository stockRepository;

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        setRepository(stockRepository);
    }

    public Stock findBySymbolAndPortfolioId(String symbol, Long portfolioId) {
        return stockRepository.findBySymbolAndPortfolioId(symbol, portfolioId);
    }

    public BigDecimal calculateTotalReturns(Stock stock, Quote quote){
        return getReturns(stock.getPrice(), quote.getCurrentPrice(), stock.getNumShares());
    }

    public BigDecimal calculateSimpleReturns(Stock stock, BigDecimal sellingPrice){
        if(!stock.getInHand()){
            throw new StockInHandException("Cannot calculate Simple Returns if Stock is still in hand");
        }
        return getReturns(stock.getPrice(), sellingPrice, stock.getNumShares());
    }

    private BigDecimal getReturns(BigDecimal buyingPrice, BigDecimal sellingPrice, Long numberOfShares){
        BigDecimal totalReturns = sellingPrice.subtract(buyingPrice);
        totalReturns = totalReturns.divide(buyingPrice, 4, BigDecimal.ROUND_HALF_DOWN);
        return totalReturns.multiply(BigDecimal.valueOf(100 * numberOfShares)).stripTrailingZeros();
    }

    public List<Stock> findByPortfolioId(Long portfolioId) {
        return stockRepository.findByPortfolioId(portfolioId);
    }

    public List<Stock> findByInHandAndPortfolioId(Boolean inHand, Long portfolioId) {
        return stockRepository.findByInHandAndPortfolioId(inHand, portfolioId);
    }

    public List<Stock> findDistinctByInHandAndPortfolioId(Boolean inHand, Long portfolioId) {
        return stockRepository.findDistinctByInHandAndPortfolioId(inHand, portfolioId);
    }
}
