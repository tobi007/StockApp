package com.tobi.stock.services.api;

import java.math.BigDecimal;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 8:37 AM
 **/

public interface Quote {
    BigDecimal getCurrentPrice();

    String getSymbol();

    String getCompanyName();

    BigDecimal getLatestPrice();

    String getLatestSource();

    Long getLatestUpdate();
}
