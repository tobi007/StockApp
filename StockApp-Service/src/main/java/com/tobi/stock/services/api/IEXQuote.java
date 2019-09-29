package com.tobi.stock.services.api;

import java.math.BigDecimal;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 26/09/2019
 * Time: 8:30 PM
 **/

public class IEXQuote implements Quote {
    
    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String sector;
    private String calculationPrice;
    private BigDecimal open;
    private Long openTime;
    private BigDecimal close;
    private Long closeTime;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal volume;
    private BigDecimal latestPrice;
    private String latestSource;
    private String latestTime;
    private Long latestUpdate;
    private BigDecimal latestVolume;
    private BigDecimal iexRealtimePrice;
    private BigDecimal iexRealtimeSize;
    private Long iexLastUpdated;
    private BigDecimal delayedPrice;
    private Long delayedPriceTime;
    private BigDecimal extendedPrice;
    private BigDecimal extendedChange;
    private BigDecimal extendedChangePercent;
    private Long extendedPriceTime;
    private BigDecimal previousClose;
    private BigDecimal previousVolume;
    private BigDecimal change;
    private BigDecimal changePercent;
    private BigDecimal iexMarketPercent;
    private BigDecimal iexVolume;
    private BigDecimal avgTotalVolume;
    private BigDecimal iexBidPrice;
    private BigDecimal iexBidSize;
    private BigDecimal iexAskPrice;
    private BigDecimal iexAskSize;
    private BigDecimal marketCap;
    private BigDecimal peRatio;
    private BigDecimal week52High;
    private BigDecimal week52Low;
    private BigDecimal ytdChange;
    private BigDecimal bidPrice;
    private BigDecimal bidSize;
    private BigDecimal askPrice;
    private BigDecimal askSize;
    private Long lastTradeTime;
    private Boolean isUSMarketOpen;

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

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(BigDecimal latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public Long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public BigDecimal getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(BigDecimal latestVolume) {
        this.latestVolume = latestVolume;
    }

    public BigDecimal getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(BigDecimal iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public BigDecimal getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(BigDecimal iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(Long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public BigDecimal getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(BigDecimal delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public Long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(Long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public BigDecimal getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(BigDecimal extendedChange) {
        this.extendedChange = extendedChange;
    }

    public BigDecimal getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(BigDecimal extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public Long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(Long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public BigDecimal getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(BigDecimal previousClose) {
        this.previousClose = previousClose;
    }

    public BigDecimal getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(BigDecimal previousVolume) {
        this.previousVolume = previousVolume;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    public BigDecimal getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(BigDecimal iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public BigDecimal getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(BigDecimal iexVolume) {
        this.iexVolume = iexVolume;
    }

    public BigDecimal getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(BigDecimal avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public BigDecimal getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(BigDecimal iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public BigDecimal getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(BigDecimal iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public BigDecimal getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(BigDecimal iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public BigDecimal getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(BigDecimal iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(BigDecimal peRatio) {
        this.peRatio = peRatio;
    }

    public BigDecimal getWeek52High() {
        return week52High;
    }

    public void setWeek52High(BigDecimal week52High) {
        this.week52High = week52High;
    }

    public BigDecimal getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(BigDecimal week52Low) {
        this.week52Low = week52Low;
    }

    public BigDecimal getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(BigDecimal ytdChange) {
        this.ytdChange = ytdChange;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getBidSize() {
        return bidSize;
    }

    public void setBidSize(BigDecimal bidSize) {
        this.bidSize = bidSize;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    public BigDecimal getAskSize() {
        return askSize;
    }

    public void setAskSize(BigDecimal askSize) {
        this.askSize = askSize;
    }

    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public Boolean getUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setUSMarketOpen(Boolean USMarketOpen) {
        isUSMarketOpen = USMarketOpen;
    }

    @Override
    public BigDecimal getCurrentPrice() {
        return this.latestPrice;
    }
}
