package com.ineda.microtote.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ineda.microtote.client.ToteError;

public class WagerResponse {
    @JsonProperty("status")
    private int status;
    private String description;
    private Ticket ticket;

    public WagerResponse() {
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean isGood() throws ResponseErrorException {
        if (status!=ToteError.SUCCESS.getCode())
            throw new ResponseErrorException(status,description);
        return true;
    }

    @Override
    public String toString() {
        return "WagerResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
