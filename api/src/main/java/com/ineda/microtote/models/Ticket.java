package com.ineda.microtote.models;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Ticket
{
    private boolean cancelled;
    @JsonProperty("cancelled_address")
    private String cancelledAddress;
    @JsonProperty("cancelled_at")
    private String cancelledAt;
    @JsonProperty("cancelled_by")
    private String cancelledBy;
    @JsonProperty("cost")
    private String toteCost;
    @JsonProperty("country_of_residence_code")
    private String countryOfResidenceCode;
    private String currency;
    @JsonProperty("meeting_id")
    private String meetingId;
    @JsonProperty("meeting_name")
    private String meetingName;
    @JsonProperty("merchant_ref")
    private String merchantRef;
    @JsonProperty("patron_id")
    private String patronId;
    private boolean quickpick;
    @JsonProperty("pool_code")
    private String poolCode;
    @JsonProperty("pool_id")
    private int poolId;
    @JsonProperty("race_id")
    private int raceId;
    @JsonProperty("race_number")
    private int raceNumber;
    private String selection;
    @JsonProperty("sell_forex_rate")
    private String sellForexRate;
    @JsonProperty("sold_address")
    private String soldAddress;
    @JsonProperty("sold_at")
    private String soldAt;
    @JsonProperty("stake")
    private String toteStake;
    private String tsn;

    public Ticket() {}

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancelledAddress() {
        return cancelledAddress;
    }

    public void setCancelledAddress(String cancelledAddress) {
        this.cancelledAddress = cancelledAddress;
    }

    public String getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(String cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public String getToteCost() {
        return toteCost;
    }

    public double getToteCostPennies() {
        return Double.parseDouble(toteCost);
    }


    public void setToteCost(String toteCost) {
        this.toteCost = toteCost;
    }

    public String getCountryOfResidenceCode() {
        return countryOfResidenceCode;
    }

    public void setCountryOfResidenceCode(String countryOfResidenceCode) {
        this.countryOfResidenceCode = countryOfResidenceCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(String merchantRef) {
        this.merchantRef = merchantRef;
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public boolean isQuickpick() {
        return quickpick;
    }

    public void setQuickpick(boolean quickpick) {
        this.quickpick = quickpick;
    }

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSellForexRate() {
        return sellForexRate;
    }

    public void setSellForexRate(String sellForexRate) {
        this.sellForexRate = sellForexRate;
    }

    public String getSoldAddress() {
        return soldAddress;
    }

    public void setSoldAddress(String soldAddress) {
        this.soldAddress = soldAddress;
    }

    public String getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(String soldAt) {
        this.soldAt = soldAt;
    }

    public String getToteStake() {
        return toteStake;
    }

    public void setToteStake(String toteStake) {
        this.toteStake = toteStake;
    }

    public String getTsn() {
        return tsn;
    }

    public void setTsn(String tsn) {
        this.tsn = tsn;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "cancelled=" + cancelled +
                ", cancelledAddress='" + cancelledAddress + '\'' +
                ", cancelledAt='" + cancelledAt + '\'' +
                ", cancelledBy='" + cancelledBy + '\'' +
                ", toteCost='" + toteCost + '\'' +
                ", countryOfResidenceCode='" + countryOfResidenceCode + '\'' +
                ", currency='" + currency + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", meetingName='" + meetingName + '\'' +
                ", merchantRef='" + merchantRef + '\'' +
                ", patronId='" + patronId + '\'' +
                ", totepick=" + quickpick +
                ", poolCode='" + poolCode + '\'' +
                ", poolId=" + poolId +
                ", raceId=" + raceId +
                ", raceNumber=" + raceNumber +
                ", selection='" + selection + '\'' +
                ", sellForexRate='" + sellForexRate + '\'' +
                ", soldAddress='" + soldAddress + '\'' +
                ", soldAt='" + soldAt + '\'' +
                ", toteStake='" + toteStake + '\'' +
                ", tsn='" + tsn + '\'' +
                '}';
    }
}
