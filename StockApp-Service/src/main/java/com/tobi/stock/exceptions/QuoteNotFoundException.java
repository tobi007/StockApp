package com.tobi.stock.exceptions;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 9:40 PM
 **/

public class QuoteNotFoundException extends RuntimeException {

    public QuoteNotFoundException(String message) {
        super(message);
    }

    public QuoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
