package com.tobi.stock.controllers.api;

import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 5:14 PM
 **/

public class Response {

    private String code;
    private String description;
    private List<Error> errors;

    public Response(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Response() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
