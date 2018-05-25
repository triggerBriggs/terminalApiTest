package com.indea.microtote;

import com.ineda.microtote.client.MicrototeClient;
import com.ineda.microtote.models.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MicrototeClientPingTest {

    private static final int tests = 100;


    private static String dir = System.getProperty("user.dir");
    private static String keysPath = dir + "/" + "api/src/test/resources/";  // Running tests when certs are in resources


    /** Now what endpoint are you wanting to connect to. !!!!, and which version of the API, and keystore password. */
    private static MicrototeClient client = new MicrototeClient(
            "https://br-roll-concourse01.int.i-neda.com:9904/", "v2"
            ,keysPath + "client-cert-key.jks"  , keysPath + "cacert.jks",
            "mark12345","Jersey.log" );



    // Can we at least get to the server and get a valid 200 http level response.
    @Test
    public void A_givenGoodStatus_whenPingJsonRequest_thenResponseStatusIsGood() {
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();

            Response response = client.getPing();
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(i + "(" + elapsedTime + " ms) Tote Ping test, response : " + response.readEntity(String.class));
            assertEquals(response.getStatus(), 200);
        }
    }

}