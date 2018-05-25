package com.ineda.terminalApi;

import com.ineda.terminalApi.client.TerminalApiClient;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalApiClientNoAuthTotePingTest {

    private static final int tests = 100;

    /** Now what endpoint are you wanting to connect to. !!!!, and which version of the API, and keystore password. */
    private static TerminalApiClient client = new TerminalApiClient(
            "http://10.14.17.23:5000/",
            "v1",
            "74ertsdfgs14",
            "password",
            "643cd362ff15427c",
            "Jersey.log" );

    // Can we get a valid 200 http level response with authentication.
    @Test
    public void A_givenGoodStatus_whenNoAuthPingJsonRequest_thenResponseStatusIsGood() {
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();
            Response response = client.postTotePingNoAuth();
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(i+"("+elapsedTime + " ms) Tote Ping test, response : "+ response.readEntity(String.class));
            assertEquals(response.getStatus(), 200);
        }
    }


}