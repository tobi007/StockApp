package com.tobi.stock.services;

import com.tobi.stock.exceptions.StockInHandException;
import com.tobi.stock.models.Stock;
import com.tobi.stock.services.api.IEXQuote;
import com.tobi.stock.utils.Generator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 1:32 AM
 **/

@RunWith(SpringRunner.class)
public class StockServiceTest {

    StockService stockService = new StockService();
    Stock generatorStock = Generator.getnerateStock();
    IEXQuote generatorIEXQuote = Generator.getnerateIEXQuote();

    @Test
    public void testWhenGivenStockAndQuoteToCalculateTotalReturnsItPasses() {
        Stock stock = generatorStock;
        IEXQuote iexQuote = generatorIEXQuote;
        Assertions.assertThat(stockService.calculateTotalReturns(stock, iexQuote))
                .isEqualByComparingTo(new BigDecimal("1427.79"));
    }

    @Test(expected = StockInHandException.class)
    public void testWhenGivenStockAndQuoteToCalculateSimpleReturnsItFailsBecauseStockNotInHand() {
        stockService.calculateSimpleReturns(new Stock(), new BigDecimal(String.valueOf(55.99)));
    }

    @Test
    public void testWhenGivenStockAndQuoteToCalculateSimpleReturnsItPasses(){
        Stock stock = generatorStock;

        Assertions.assertThat(stockService.calculateSimpleReturns(stock, new BigDecimal(String.valueOf(55.99))))
        .isEqualByComparingTo(new BigDecimal("1427.79"));
    }


    @Test
    public void testWhenYouFindStockItReturnsTheStock(){
        Assertions.fail("Not Implemented");
    }

    @Test
    public void testWhenYouFindStockItReturnsEmpty(){
        Assertions.fail("Not Implemented");
    }
}
