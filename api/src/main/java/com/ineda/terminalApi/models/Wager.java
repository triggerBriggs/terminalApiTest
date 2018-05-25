package com.ineda.terminalApi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wager {
    @JsonProperty("merchant_ref")
    private String merchantReference;
    @JsonProperty("card_id")
    private int cardId;
    @JsonProperty("pool_id")
    private int poolId;
    private String selection;
    private String stake;
    private String cost;
    private String currency;

    @JsonCreator
    public Wager(){}

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getStake() {
        return stake;
    }

    public void setStake(String stake) {
        this.stake = stake;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    @Override
    public String toString() {
        return "Wager{" +
                "merchantReference='" + merchantReference + '\'' +
                ", cardId=" + cardId +
                ", poolId=" + poolId +
                ", selection='" + selection + '\'' +
                ", stake='" + stake + '\'' +
                ", cost='" + cost + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
