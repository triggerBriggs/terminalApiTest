package com.ineda.terminalApi.models;

import java.util.UUID;

public class WagerBuilder {
    private String merchantReference;
    private int cardId;
    private int poolId;
    private String selection;
    private String mutation;
    private String stake;
    private String cost;
    private String currency;
    private String patronId;
    private String countryOfResidenceCode;

    public WagerBuilder withCardId(int cardId) {
        this.cardId = cardId;
        return this;
    }

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

    public WagerBuilder withMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
        return this;
    }

    public Wager build(){
        Wager w = new Wager();
        w.setMerchantReference(merchantReference);
        w.setPoolId(poolId);
        w.setSelection(selection);
        w.setStake(stake);
        w.setCost(cost);
        w.setCurrency(currency);
        return w ;
    }

    /** You will need to find a WIN pool to wager into. */
    private static final int A_CARD_ID = 2354;
    private static final int A_WIN_POOL_ID = 237206;
    public Wager buildWinTest(){
        Wager w = new Wager();
        w.setMerchantReference(UUID.randomUUID().toString());
        w.setCardId(A_CARD_ID);
        w.setPoolId(A_WIN_POOL_ID);
        w.setSelection("1,3");
        w.setStake("2.00");
        w.setCost("4.00");
        w.setCurrency("GBP");
        return w ;
    }

    /** You will need to find a WIN pool to wager into. */
    private static final int A_EXA_POOL_ID = 237212;
    public Wager buildExaTest(){
        Wager w = new Wager();
        w.setMerchantReference(UUID.randomUUID().toString());
        w.setCardId(A_CARD_ID);
        w.setPoolId(A_EXA_POOL_ID);
        w.setSelection("1,3/2,4");
        w.setStake("1.00");
        w.setCost("4.00");
        w.setCurrency("GBP");
        return w ;
    }

    /** You will need to find a TRI pool to wager into.  */
    private static final int A_TRI_POOL_ID = 237215;
    public Wager buildTriTest(){
        Wager w = new Wager();
        w.setMerchantReference(UUID.randomUUID().toString());
        w.setCardId(A_CARD_ID);
        w.setPoolId(A_TRI_POOL_ID);
        w.setSelection("1,3/2/4");
        w.setStake("1.00");
        w.setCost("2.00");
        w.setCurrency("GBP");
        return w ;
    }

}
