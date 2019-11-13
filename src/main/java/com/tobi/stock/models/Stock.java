package com.tobi.stock.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobi.stock.enums.LatestPriceSourceType;
import com.tobi.stock.services.api.IEXQuote;
import com.tobi.stock.services.api.Quote;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 7:44 PM
 **/

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Symbol is mandatory")
    private String symbol;

    @NotBlank(message = "Company's Name is mandatory")
    private String companyName;

    @NotNull(message = "price is mandatory")
    private BigDecimal price;

    @NotNull(message = "latestSource is mandatory")
    private LatestPriceSourceType latestSource;

    @NotNull(message = "latestSourceUpdate is mandatory")
    private Date latestSourceUpdate;

    @NotNull(message = "created On is mandatory")
    private Date createdOn;

    private Boolean inHand = false;

    private Long numShares = 0L;

    private BigDecimal soldAt;
    private Date soldAtOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    @JsonIgnore
    private Portfolio portfolio;

    public Stock() {
    }

    public Stock(Quote quote) {
        this.symbol = quote.getSymbol();
        this.companyName = quote.getCompanyName();
        this.price = quote.getLatestPrice();
        this.latestSource = LatestPriceSourceType.valueOf(quote.getLatestSource());
        this.latestSourceUpdate = new Date(quote.getLatestUpdate());
        this.createdOn = new Date();
        this.inHand = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LatestPriceSourceType getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(LatestPriceSourceType latestSource) {
        this.latestSource = latestSource;
    }

    public Date getLatestSourceUpdate() {
        return latestSourceUpdate;
    }

    public void setLatestSourceUpdate(Date latestSourceUpdate) {
        this.latestSourceUpdate = latestSourceUpdate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getInHand() {
        return inHand;
    }

    public void setInHand(Boolean inHand) {
        this.inHand = inHand;
    }

    public Long getNumShares() {
        return numShares;
    }

    public void setNumShares(Long numShares) {
        this.numShares = numShares;
    }

    public BigDecimal getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(BigDecimal soldAt) {
        this.soldAt = soldAt;
    }

    public Date getSoldAtOn() {
        return soldAtOn;
    }

    public void setSoldAtOn(Date soldAtOn) {
        this.soldAtOn = soldAtOn;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
