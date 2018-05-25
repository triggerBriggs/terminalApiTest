package com.ineda.terminalApi;

import com.ineda.terminalApi.client.TerminalApiClient;
import com.ineda.terminalApi.models.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalApiClientWagerWinTest {


    private static String dir = System.getProperty("user.dir");
    private static final int tests = 1000;

    /** A simple wager object to use for wagering */
    private static Wager wager;
    private static WagerResponse wagerResponse;

    /** Now what endpoint are you wanting to connect to. !!!!, and which version of the API, and keystore password. */
    private static TerminalApiClient client = new TerminalApiClient(
            "http://10.14.17.23:5000/",
            "v1",
            "7414",
            "password",
            "643cd362ff15427c",
            "Jersey.log" );

    // Wager the ticket.
    @Test
    public void C_givenGoodResponse_whenWagerRequest_thenResponseObjectCreated() {
        /** Get a default wager for testing */
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();
            wager = new WagerBuilder().buildWinTest();
            Response response = client.postWager(wager);
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                wagerResponse = response.readEntity(WagerResponse.class);
                if (wagerResponse.getTicket()!=null){
                    if (wagerResponse.getTicket().getTsn()!=null&&wagerResponse.getTicket().getTsn().length()>0){
                        System.out.println(i+"("+elapsedTime + " ms) win bet. " + wager + " response : " + wagerResponse);
                    }else {
                        System.err.println(i + "(" + elapsedTime + " ms) win bet. NO TSN. " + wager + " response : " + wagerResponse);
                    }
                }else{
                    System.err.println(i+"("+elapsedTime + " ms) win bet. NO Ticket details. " + wager + " response : " + wagerResponse);
                }
            } else {
                System.err.println(i+"("+elapsedTime + " ms) win bet. " + wager + " response : " + response.readEntity(String.class));
            }
            assertThat(wagerResponse.getTicket().getTsn(), notNullValue());
        }
    }


}