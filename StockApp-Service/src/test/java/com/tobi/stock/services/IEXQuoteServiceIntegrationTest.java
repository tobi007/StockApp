package com.tobi.stock.services;

import com.tobi.stock.exceptions.QuoteNotFoundException;
import com.tobi.stock.services.api.IEXQuote;
import com.tobi.stock.services.api.Quote;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 8:35 PM
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class IEXQuoteServiceIntegrationTest {


    @Autowired
    IEXQuoteService iexQuoteService;

    @Test(expected = QuoteNotFoundException.class)
    public void testIfUnknownSymbolIsSentReturnQuoteNotFoundException() {
        iexQuoteService.getQuote("hfdjskdjfdkj");
    }

    @Test(expected = QuoteNotFoundException.class)
    public void testIfNoSymbolIsSentReturnQuoteNotFoundException() {
        iexQuoteService.getQuote("");
    }

    @Test
    public void testIfKnownSymbolIsSentReturnIEXQuote() {
        Quote iexQuote = iexQuoteService.getQuote("msft");
        
        Assertions.assertThat(iexQuote).isNotNull();
        Assertions.assertThat(iexQuote.getSymbol()).isEqualToIgnoringCase("MSFT");
        Assertions.assertThat(iexQuote.getCompanyName()).isEqualToIgnoringCase("Microsoft Corp.");
    }

    @Test
    public void testIfKnownMultipleSymbolIsSentReturnIEXQuote() throws IOException {
        List<String> symbols = Arrays.asList("aapl", "msft");
        Map<String, Quote> stringIEXQuoteMap = iexQuoteService.getQuote(symbols);

        Assertions.assertThat(stringIEXQuoteMap.size()).isEqualTo(2);
        Assertions.assertThat(stringIEXQuoteMap.get("aapl")).isNotNull();
        Assertions.assertThat(stringIEXQuoteMap.get("msft")).isNotNull();
    }


}
