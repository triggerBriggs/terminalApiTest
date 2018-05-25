package com.ineda.microtote.models;

import java.util.UUID;

public class WagerBuilder {
    private String merchantReference;
    private int poolId;
    private String selection;
    private String mutation;
    private String stake;
    private String cost;
    private String currency;
    private String patronId;
    private String countryOfResidenceCode;

    public WagerBuilder withPoolId(int poolId) {
        this.poolId = poolId;
        return this;
    }

    public WagerBuilder withSelection(String selection) {
        this.selection = selection;
        return this;
    }

    public WagerBuilder withMutation(String mutation) {
        this.mutation = mutation;
        return this;
    }

    public WagerBuilder withStake(String stake) {
        this.stake = stake;
        return this;
    }

    public WagerBuilder withCost(String cost) {
        this.cost = cost;
        return this;
    }

    public WagerBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public WagerBuilder withPatronId(String patronId) {
        this.patronId = patronId;
        return this;
    }

    public WagerBuilder withCountryOfResidenceCode(String countryOfResidenceCode) {
        this.countryOfResidenceCode = countryOfResidenceCode;
        return this;
    }

    public WagerBuilder withMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
        return this;
    }

    public Wager build(){
        Wager w = new Wager();
        w.setMerchantReference(merchantReference);
        w.setPoolId(poolId);
        w.setSelection(selection);
        w.setMutation(mutation);
        w.setStake(stake);
        w.setCost(cost);
        w.setCurrency(currency);
        w.setPatronId(patronId);
        w.setCountryOfResidenceCode(countryOfResidenceCode);
        return w ;
    }

    /** You will need to find a WIN pool to wager into. Just a random pool on rolling that seems to be open. */
    private static final int A_POOL_ID = 80608;
    public Wager buildTestDefault(){
        Wager w = new Wager();
        w.setMerchantReference(UUID.randomUUID().toString());
        w.setPoolId(A_POOL_ID);
        w.setSelection("1,3");
        w.setMutation("");
        w.setStake("2.00");
        w.setCost("4.00");
        w.setCurrency("GBP");
        w.setPatronId("");
        w.setCountryOfResidenceCode("");
        return w ;
    }
}
