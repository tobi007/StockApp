package com.tobi.stock.services.api;

import java.util.Map;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 29/09/2019
 * Time: 2:28 PM
 **/

public class IEXBatchQuote {

    private Map<String, Map<String, Quote>> AAPL;

    public Map<String, Map<String, Quote>> getSymbol() {
        return AAPL;
    }

    public void setSymbol(Map<String, Map<String, Quote>> symbol) {
        this.AAPL = symbol;
    }
}
