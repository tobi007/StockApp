package com.tobi.stock.exceptions;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 8:59 AM
 **/

public class StockInHandException extends RuntimeException {

    public StockInHandException(String message) {
        super(message);
    }

    public StockInHandException(String message, Throwable cause) {
        super(message, cause);
    }
}
