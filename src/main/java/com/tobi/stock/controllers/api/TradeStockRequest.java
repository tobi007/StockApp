package com.tobi.stock.controllers.api;

import javax.validation.constraints.NotNull;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 28/09/2019
 * Time: 3:23 AM
 **/

public class TradeStockRequest {

    @NotNull(message = "symbol is mandatory")
    private String symbol;
    @NotNull(message = "numberOfShares is mandatory")
    private Long numberOfShares;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(Long numberOfShares) {
        this.numberOfShares = numberOfShares;
    }
}
