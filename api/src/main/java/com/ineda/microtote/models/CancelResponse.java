package com.ineda.microtote.models;

import com.ineda.microtote.client.ToteError;

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
        if (status!= ToteError.SUCCESS.getCode())
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
