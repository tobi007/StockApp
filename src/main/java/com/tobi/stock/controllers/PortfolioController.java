package com.tobi.stock.controllers;

import com.tobi.stock.controllers.api.TradeStockRequest;
import com.tobi.stock.exceptions.QuoteNotFoundException;
import com.tobi.stock.models.Portfolio;
import com.tobi.stock.models.Stock;
import com.tobi.stock.services.IEXQuoteService;
import com.tobi.stock.services.PortfolioService;
import com.tobi.stock.services.QuoteService;
import com.tobi.stock.services.api.IEXQuote;
import com.tobi.stock.services.api.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.Date;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 28/09/2019
 * Time: 2:52 AM
 **/

@RestController
@RequestMapping("/api/stock")
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    private PortfolioService portfolioService;

    @Autowired
    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/portfolio")
    public ResponseEntity getInvestorPortfolio(Principal principal) {
        Portfolio portfolio = portfolioService.getPortfolioByOwner(principal.getName());
        portfolio.setCurrentValue(portfolioService.calculateCurrentValue(portfolio.getOwner()));
        return ok(portfolio);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/quote/{symbol}")
    public ResponseEntity lookUpQuote(@PathVariable("symbol") String symbol) {
        logger.info(symbol);
        return ok(portfolioService.getQuoteInfo(symbol));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/buy")
    public ResponseEntity buyStock(@Valid @RequestBody TradeStockRequest TradeStockRequest, Principal principal) throws QuoteNotFoundException {
        logger.info(String.format("About to buy %s worth of %s shares", TradeStockRequest.getNumberOfShares(), TradeStockRequest.getSymbol()));

        Quote quote = portfolioService.getQuoteInfo(TradeStockRequest.getSymbol());
        Stock stock = new Stock(quote);
        Portfolio portfolio = portfolioService.buyStock(stock, TradeStockRequest.getNumberOfShares(), principal.getName());
        portfolio.setCurrentValue(portfolioService.calculateCurrentValue(portfolio.getOwner()));
        return ok(portfolio);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/sell")
    public ResponseEntity sellStock(@Valid @RequestBody TradeStockRequest TradeStockRequest, Principal principal) throws QuoteNotFoundException {
        logger.info(String.format("About to sell %s worth of %s shares", TradeStockRequest.getNumberOfShares(), TradeStockRequest.getSymbol()));

        Quote quote = portfolioService.getQuoteInfo(TradeStockRequest.getSymbol());
        Stock stock = new Stock(quote);
        Portfolio portfolio = portfolioService.sellStock(stock, TradeStockRequest.getNumberOfShares(), principal.getName());
        portfolio.setCurrentValue(portfolioService.calculateCurrentValue(portfolio.getOwner()));
        return ok(portfolio);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/inhand")
    public ResponseEntity getInHandStock(Principal principal) {
        return ok(portfolioService.getInHandStocks(principal.getName()));
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/transactions")
    public ResponseEntity getTransactions(@RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
                                          @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to, Principal principal) {
        return ok(portfolioService.getTransactions(principal.getName(), from, to));
    }
}
