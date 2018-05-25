package com.ineda.terminalApi.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Ticket
{
    public static final String SERIALIZED_NAME = "ticket";

    @JsonProperty("card_id")
    protected int cardId;
    @JsonProperty("pool_id")
    protected int poolId;
    protected String selection;
    protected String mutation;

    @JsonProperty("customer_stake")
    protected BigDecimal stake;
    @JsonProperty("customer_cost")
    protected BigDecimal cost;
    @JsonProperty("customer_winnings")
    protected BigDecimal winnings;
    @JsonProperty("customer_refund")
    protected BigDecimal refund;
    @JsonProperty("customer_currency")
    protected String currency;

    @JsonProperty("tote_stake")
    protected BigDecimal toteStake;
    @JsonProperty("tote_cost")
    protected BigDecimal toteCost;
    @JsonProperty("tote_currency")
    protected String toteCurrency;

    @JsonProperty("merchant_ref")
    protected String merchantReference;
    protected String tsn;

    protected boolean cancelled;
    protected boolean paid;
    protected boolean reconciled;

    @JsonProperty("payment_method")
    protected String paymentMethod;


    public Ticket(){};

    public static String getSerializedName() {
        return SERIALIZED_NAME;
    }

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

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getWinnings() {
        return winnings;
    }

    public void setWinnings(BigDecimal winnings) {
        this.winnings = winnings;
    }

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getToteStake() {
        return toteStake;
    }

    public void setToteStake(BigDecimal toteStake) {
        this.toteStake = toteStake;
    }

    public BigDecimal getToteCost() {
        return toteCost;
    }

    public void setToteCost(BigDecimal toteCost) {
        this.toteCost = toteCost;
    }

    public String getToteCurrency() {
        return toteCurrency;
    }

    public void setToteCurrency(String toteCurrency) {
        this.toteCurrency = toteCurrency;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    public String getTsn() {
        return tsn;
    }

    public void setTsn(String tsn) {
        this.tsn = tsn;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isReconciled() {
        return reconciled;
    }

    public void setReconciled(boolean reconciled) {
        this.reconciled = reconciled;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "cardId=" + cardId +
                ", poolId=" + poolId +
                ", selection='" + selection + '\'' +
                ", mutation='" + mutation + '\'' +
                ", stake=" + stake +
                ", cost=" + cost +
                ", winnings=" + winnings +
                ", refund=" + refund +
                ", currency='" + currency + '\'' +
                ", toteStake=" + toteStake +
                ", toteCost=" + toteCost +
                ", toteCurrency='" + toteCurrency + '\'' +
                ", merchantReference='" + merchantReference + '\'' +
                ", tsn='" + tsn + '\'' +
                ", cancelled=" + cancelled +
                ", paid=" + paid +
                ", reconciled=" + reconciled +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
