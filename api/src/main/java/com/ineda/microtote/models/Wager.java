package com.ineda.microtote.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wager {
    @JsonIgnore
    private String merchantReference;

    @JsonProperty("pool_id")
    private int poolId;
    private String selection;
    private String mutation;
    private String stake;
    private String cost;
    private String currency;
    @JsonProperty("patron_id")
    private String patronId;
    @JsonProperty("country_of_residence_code")
    private String countryOfResidenceCode;

    @JsonCreator
    public Wager(){}

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

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
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

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getCountryOfResidenceCode() {
        return countryOfResidenceCode;
    }

    public void setCountryOfResidenceCode(String countryOfResidenceCode) {
        this.countryOfResidenceCode = countryOfResidenceCode;
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
                "poolId=" + poolId +
                ", selection='" + selection + '\'' +
                ", mutation='" + mutation + '\'' +
                ", stake='" + stake + '\'' +
                ", cost='" + cost + '\'' +
                ", currency='" + currency + '\'' +
                ", patronId='" + patronId + '\'' +
                ", countryOfResidenceCode='" + countryOfResidenceCode + '\'' +
                ", merchantReference='" + merchantReference + '\'' +
                '}';
    }
}
