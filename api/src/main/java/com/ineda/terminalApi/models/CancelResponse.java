package com.ineda.terminalApi.models;

import com.ineda.terminalApi.client.TerminalApiError;

public class CancelResponse {
    private int status;
    private String description;

    public CancelResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGood() throws ResponseErrorException {
        if (status!= TerminalApiError.SUCCESS.getCode())
            throw new ResponseErrorException(status,description);
        return true;
    }

    @Override
    public String toString() {
        return "CancelResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
