package com.ineda.terminalApi;

import com.ineda.terminalApi.client.TerminalApiClient;
import com.ineda.terminalApi.models.CancelResponse;
import com.ineda.terminalApi.models.Wager;
import com.ineda.terminalApi.models.WagerBuilder;
import com.ineda.terminalApi.models.WagerResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalApiClientSingleWagerWinTest {


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

    // Wager the ticket.
    @Test
    public void C_givenGoodResponse_whenWagerRequest_thenResponseObjectCreated() {
        /** Get a default wager for testing */
            long startTime = System.currentTimeMillis();
            wager = new WagerBuilder().buildWinTest();
            System.out.println("Wagering wager " + wager);
            Response response = client.postWager(wager);
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                //System.out.println("("+elapsedTime + " ms) EXA Wager " + wager + " response : " + response.readEntity(String.class));
                wagerResponse = response.readEntity(WagerResponse.class);
                System.out.println("Wager response (" + elapsedTime + " ms) : " + wagerResponse);
            } else {
                System.out.println("Wager response (" + elapsedTime + " ms) : " + response.readEntity(String.class));
            }

            assertThat(wagerResponse, notNullValue());
    }

     //Now Check wager response has a good status.
    @Test
    public void D_givenGoodResponse_whenWagerRequest_thenIsStatusGood() {
        assertEquals(wagerResponse.getStatus_code(), 200);
    }

    @Test
    public void E_givenGoodStatus_whenWagerRequest_thenTicketObjectIsGood() {
        assertThat(wagerResponse.getTicket(), notNullValue());
    }

    @Test
    public void F_givenTicket_whenTsnIsPresent_thenTicketIsGood() {
        assertThat(wagerResponse.getTicket().getTsn(), notNullValue());
    }

}