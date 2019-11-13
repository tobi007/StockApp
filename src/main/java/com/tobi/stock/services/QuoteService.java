package com.tobi.stock.services;

import com.tobi.stock.services.api.Quote;

import java.util.List;
import java.util.Map;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 29/09/2019
 * Time: 7:30 PM
 **/

public interface QuoteService {
    Quote getQuote(String symbol);
    Map<String, Quote> getQuote(List<String> symbols);
}
