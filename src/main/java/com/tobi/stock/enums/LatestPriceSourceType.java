package com.tobi.stock.enums;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 11:30 PM
 **/

public enum  LatestPriceSourceType {
    IEXRealTimePrice("IEX real time price"),
    IEX15MinuteDelayedPrice("15 minute delayed price"),
    Close("Close"),
    PreviousClose("Previous close");

    private String code;

    LatestPriceSourceType(String code) {
        this.code = code;
    }

    public boolean isEqual(String position) {
        return this.code.equals(position);
    }

    public String getCode() {
        return code;
    }
}
