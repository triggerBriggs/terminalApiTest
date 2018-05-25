package com.ineda.terminalApi.models;

public class PingResponse {

    private int status_code;
    private String description;

    public PingResponse() {
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PingResponse{" +
                "status_code=" + status_code +
                ", description='" + description + '\'' +
                '}';
    }
}
