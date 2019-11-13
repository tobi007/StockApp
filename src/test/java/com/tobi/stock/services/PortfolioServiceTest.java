package com.tobi.stock.services;

import com.tobi.stock.exceptions.NotEnoughStockSharesInPortfolio;
import com.tobi.stock.exceptions.PortfolioNotFoundException;
import com.tobi.stock.exceptions.StockNotInHandException;
import com.tobi.stock.models.Stock;
import com.tobi.stock.repositories.PortfolioRepository;
import com.tobi.stock.repositories.StockRepository;
import com.tobi.stock.repositories.TransactionRepository;
import com.tobi.stock.utils.Generator;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 2:50 PM
 **/

@RunWith(SpringRunner.class)
public class PortfolioServiceTest {

    @TestConfiguration
    static class PortfolioServiceTestContextConfiguration {

        @Bean
        public PortfolioService portfolioService() {
            return new PortfolioService();
        }

        @Bean
        public StockService stockService() {

            return new StockService();
        }

        @Bean
        public IEXQuoteService iexQuoteService() {

            return new IEXQuoteService();
        }

        @Bean
        public TransactionService transactionService() {
            return new TransactionService();
        }
    }

    @Autowired
    PortfolioService portfolioService;
    @Autowired
    StockService stockService;

    @MockBean
    PortfolioRepository portfolioRepository;
    @MockBean
    StockRepository stockRepository;
    @MockBean
    TransactionRepository transactionRepository;

    Stock notEnoughStockSharesInPortfolio;
    Stock stockNotInHandException;
    @Before
    public void setUp() {
        Mockito.when(portfolioRepository.findByOwner(Mockito.eq("NoPortfolio")))
                .thenReturn(null);

        Mockito.when(portfolioRepository.findByOwner(Mockito.eq("Exist")))
                .thenReturn(Generator.generatePortfolio());

        notEnoughStockSharesInPortfolio = Generator.getnerateStock();
        notEnoughStockSharesInPortfolio.setId(200l);
        notEnoughStockSharesInPortfolio.setNumShares(10l);
        notEnoughStockSharesInPortfolio.setSymbol("msft");
        Mockito.when(stockService.findBySymbolAndPortfolioId(Mockito.eq("msft"), Mockito.eq(200l)))
                .thenReturn(notEnoughStockSharesInPortfolio);

        stockNotInHandException = Generator.getnerateStock();
        stockNotInHandException.setId(200l);
        stockNotInHandException.setSymbol("aapl");
        stockNotInHandException.setNumShares(134l);
        stockNotInHandException.setInHand(false);
        Mockito.when(stockService.findBySymbolAndPortfolioId(Mockito.eq("aapl"), Mockito.eq(200l)))
                .thenReturn(stockNotInHandException);
    }

    @Test(expected = PortfolioNotFoundException.class)
    public void testWhenYouSellStockItReturnsPortfolioNotFoundException(){
        portfolioService.sellStock(new Stock(), 23l, "NoPortfolio");
    }

    @Test(expected = NotEnoughStockSharesInPortfolio.class)
    public void testWhenYouSellStockItReturnsNotEnoughStockSharesInPortfolio(){
        portfolioService.sellStock(notEnoughStockSharesInPortfolio, 23l, "Exist");
    }


    @Test(expected = StockNotInHandException.class)
    public void testWhenYouSellStockItReturnsStockNotInHandException(){
        portfolioService.sellStock(stockNotInHandException, 23l, "Exist");
    }

    @Test
    public void testWhenYouBuyStockAllTheRequiredPortfolioCHnages(){
        Assertions.fail("Not Implemented");
    }

    @Test
    public void testWhenYouSellStockAllTheRequiredPortfolioCHnages(){
        Assertions.fail("Not Implemented");
    }
}
