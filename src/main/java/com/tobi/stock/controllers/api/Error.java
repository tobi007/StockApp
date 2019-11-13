package com.tobi.stock.controllers.api;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 6:50 PM
 **/

public class Error {
    private String fieldName;
    private String message;

    public Error(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
