package com.ineda.microtote.models;

public class PingResponse {
    private int status;
    private String description;

    public PingResponse() {
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

    @Override
    public String toString() {
        return "PingResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
