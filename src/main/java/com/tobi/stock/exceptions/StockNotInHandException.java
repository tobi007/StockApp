package com.tobi.stock.exceptions;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 8:43 AM
 **/

public class StockNotInHandException extends RuntimeException{

    public StockNotInHandException(String message) {
        super(message);
    }

    public StockNotInHandException(String message, Throwable cause) {
        super(message, cause);
    }
}
