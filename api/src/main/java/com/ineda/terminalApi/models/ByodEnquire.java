package com.ineda.terminalApi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ByodEnquire {
    @JsonProperty("session_token")
    private String sessioToken;


    @JsonCreator
    public ByodEnquire(String token){
        this.sessioToken = token;
    }

    public String getSessioToken() {
        return sessioToken;
    }

    public void setSessioToken(String sessioToken) {
        this.sessioToken = sessioToken;
    }

    @Override
    public String toString() {
        return "ByodEnquire{" +
                "sessioToken='" + sessioToken + '\'' +
                '}';
    }
}
