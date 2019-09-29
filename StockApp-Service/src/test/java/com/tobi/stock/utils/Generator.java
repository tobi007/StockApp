package com.tobi.stock.utils;

import com.tobi.stock.models.Portfolio;
import com.tobi.stock.models.Stock;
import com.tobi.stock.services.api.IEXQuote;

import java.math.BigDecimal;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 28/09/2019
 * Time: 2:00 AM
 **/

public abstract class Generator {

    public static Stock getnerateStock() {
        Stock stock = new Stock();
        stock.setPrice(new BigDecimal(String.valueOf(33.33)));
        stock.setNumShares(21l);
        stock.setInHand(true);
        return stock;
    }

    public static IEXQuote getnerateIEXQuote() {
        IEXQuote iexQuote = new IEXQuote();
        iexQuote.setLatestPrice(new BigDecimal(String.valueOf(55.99)));
        return iexQuote;
    }

    public static Portfolio generatePortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(200l);

        return portfolio;
    }
}
