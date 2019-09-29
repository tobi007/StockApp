package com.tobi.stock.exceptions;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 4:11 PM
 **/

public class NotEnoughStockSharesInPortfolio extends RuntimeException {

    public NotEnoughStockSharesInPortfolio(String message) {
        super(message);
    }

    public NotEnoughStockSharesInPortfolio(String message, Throwable cause) {
        super(message, cause);
    }
}
