package com.ineda.terminalApi;

import com.ineda.terminalApi.client.TerminalApiClient;
import com.ineda.terminalApi.models.*;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalApiClientPingTest {


    private static String dir = System.getProperty("user.dir");
    private static final int tests = 1000;

    /** A simple wager object to use for wagering */
    private static Wager wager;
    private static WagerResponse wagerResponse;
    private static WagerResponse enquireResponse;
    private static CancelResponse cancelResponse;


    /** Now what endpoint are you wanting to connect to. !!!!, and which version of the API, and keystore password. */
    private static TerminalApiClient client = new TerminalApiClient(
            "http://10.14.17.23:5000/",
            "v1",
            "7414",
            "password",
            "643cd362ff15427c",
            "Jersey.log" );

    // Can we at least get to the server and get a valid 200 http level response.
    @Test
    public void A_givenGoodStatus_whenPingJsonRequest_thenResponseStatusIsGood() {
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();
            Response response = client.postPing();
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(i + ". Ping Response (" + elapsedTime + " ms) : " + response.getStatus());
            assertEquals(response.getStatus(), 200);
        }
    }

    // Does hitting the API ping return a good ping object, with or without authentication.
    @Test
    public void B_givenGoodStatus_whenPingJsonRequest_thenPingObjectCreated() {
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();
            Response response = client.postPing();
            PingResponse pingResponse = response.readEntity(PingResponse.class);
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(i + ". Ping Object Response (" + elapsedTime + " ms) : " + pingResponse.toString());
            assertThat(pingResponse, notNullValue());
        }
    }

}