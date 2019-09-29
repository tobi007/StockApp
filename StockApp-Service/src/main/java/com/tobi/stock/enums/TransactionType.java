package com.tobi.stock.enums;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 5:00 PM
 **/

public enum TransactionType {
    C("CREDIT"),
    D("DEBIT");

    private String code;

    TransactionType(String code) {
        this.code = code;
    }

    public boolean isEqual(String position) {
        return this.code.equals(position);
    }

    public String getCode() {
        return code;
    }
}
