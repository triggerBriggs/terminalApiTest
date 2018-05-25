package com.indea.microtote;

import com.ineda.microtote.client.MicrototeClient;
import com.ineda.microtote.models.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MicrototeClientTest {


    private static String dir = System.getProperty("user.dir");
    private static String keysPath = dir + "/" + "api/src/test/resources/";  // Running tests when certs are in resources


    /** Now what endpoint are you wanting to connect to. !!!!, and which version of the API, and keystore password. */
    private static MicrototeClient client = new MicrototeClient(
            "https://br-roll-concourse01.int.i-neda.com:9904/", "v2"
            ,keysPath + "client-cert-key.jks"  , keysPath + "cacert.jks",
            "mark12345","Jersey.log" );


    /** You will need to find a WIN pool to wager into.
     * this has been open for ages on rolling.*/

    /**
     * Handy query for ya!
     *
     select pool_oid, card_oid, enabled,pool_code, status from  pool where provider='MICROTOTE' and date(created_dt)>curdate()-1 and pool_code ='WIN';

     select * from pool_mapping where pool_oid = {one of the pool_oid's };

     then copy the module_pool_key to the constant below.

     */
    private static int A_POOL_ID = 80608;

    /** A simple wager object to use for wagering */
    private static Wager wager;
    private static WagerResponse wagerResponse;
    private static WagerResponse enquireResponse;
    private static CancelResponse cancelResponse;

    // Can we at least get to the server and get a valid 200 http level response.
    @Test
    public void A_givenGoodStatus_whenPingJsonRequest_thenResponseStatusIsGood() {
        Response response = client.getPing();
        System.out.println("PingResponse available " + response.getStatus() + "\n");
        assertEquals(response.getStatus(), 200);
    }

    // Does hitting the API ping return a good ping object
    @Test
    public void B_givenGoodStatus_whenPingJsonRequest_thenPingObjectCreated() {
        Response response = client.getPing();
        PingResponse pingResponse = response.readEntity(PingResponse.class);
        System.out.println("PingResponse response " + pingResponse + "\n");
        assertThat(pingResponse, notNullValue());
    }

    // Wager the ticket.
    @Test
    public void C_givenGoodResponse_whenWagerRequest_thenResponseObjectCreated() {
        /** Get a default wager for testing */
        wager = new WagerBuilder().buildTestDefault();
        System.out.println("Wagering wager " + wager);
        Response response = client.postWager(wager);
        wagerResponse = response.readEntity(WagerResponse.class);
        System.out.println("Wager response " + wagerResponse + "\n");
        assertThat(wagerResponse, notNullValue());
    }

    // Now Check wager response has a good status.
    @Test
    public void D_givenGoodResponse_whenWagerRequest_thenIsStatusGood() {
        assertEquals(wagerResponse.getStatus(), 200);
    }

    @Test
    public void E_givenGoodStatus_whenWagerRequest_thenTicketObjectIsGood() {
        assertThat(wagerResponse.getTicket(), notNullValue());
    }

    // Now enguire the ticket we just wagered.
    @Test
    public void F_givenGoodStatus_whenEnquiredTicket_thenResponseObjectCreated() {
        System.out.println("Enquiring ticket " + wager.getMerchantReference());
        Response response = client.getWager(wager.getMerchantReference());
        enquireResponse = response.readEntity(WagerResponse.class);
        System.out.println("Enquire response " + enquireResponse + "\n");
        assertThat(enquireResponse, notNullValue());
    }

    // Now Check enquire response has a good status.
    @Test
    public void G_givenGoodResponse_whenEnquireRequest_thenIsStatusGood() {
        assertEquals(enquireResponse.getStatus(), 200);
    }

    @Test
    public void H_givenGoodStatus_whenEnquireRequest_thenTicketObjectIsGood() {
        assertThat(enquireResponse.getTicket(), notNullValue());
    }

    // Now cancel the ticket we just wagered.
    @Test
    public void I_givenGoodStatus_whenCancelTicket_thenResponseObjectIsGood() {
        System.out.println("Cancelling ticket " + wager.getMerchantReference());
        Response response = client.deleteWager(wager.getMerchantReference());
        cancelResponse = response.readEntity(CancelResponse.class);
        System.out.println("Cancel response " + cancelResponse + "\n");
        assertThat(cancelResponse, notNullValue());
    }

    // Now Check cancel response has a good status.
    @Test
    public void J_givenGoodResponse_whenEnquireRequest_thenIsStatusGood() {
        assertEquals(cancelResponse.getStatus(), 200);
    }

}