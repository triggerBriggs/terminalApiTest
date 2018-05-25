package com.ineda.terminalApi.models;

import com.ineda.terminalApi.client.TerminalApiError;

public class WagerResponse {
    private int status_code;
    private String description;
    private String detail;
    private Ticket ticket;

    public WagerResponse() {
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getDetail() { return detail; }

    public String getDescription() {
        return description;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean isGood() throws ResponseErrorException {
        if (status_code!= TerminalApiError.SUCCESS.getCode())
            throw new ResponseErrorException(status_code,description);
        return true;
    }

    @Override
    public String toString() {
        return "WagerResponse{" +
                "status_code=" + status_code +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
