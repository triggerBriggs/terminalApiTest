package com.ineda.terminalApi.client;

import com.ineda.terminalApi.models.ByodEnquire;
import com.ineda.terminalApi.models.Wager;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.message.GZipEncoder;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TerminalApiClient {
    private static final Logger log = Logger.getLogger( TerminalApiClient.class.getName() );
    private static FileHandler fh = null;

    String postUrl;
	String apiVersion;
    String deviceId;
    SSLContext sslContext;

    Client client;
    //TODO, remove we dont need it, just here for reference.
    //TODO String micrototeClientIdHeaderValue = "" + DatatypeConverter.printBase64Binary(String.valueOf(micrototeClientId).getBytes());


    public static void initLogger(String logPath){
        try {
            fh=new FileHandler(logPath, false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
        l.setLevel(Level.FINE);
    }



    /** CANT MAKE THIS BOLD ENOUGH, the keystore password is assumed to be the same for all entries, that is everything!!
     * including when you joined your client cert and key as documented in confluence. here.
     *
     * https://i-neda.atlassian.net/wiki/spaces/INEDA/pages/431587331
     *
     * If you want to use different passwords you will need to pass all these and use them as you see fit. It is also assumed the
     * two jks files needed are put into the root of this project. ( If not change the keysPath above. )
     *
     * @param postUrl The endpoint for the API
     * @param apiVersion The version of the API you want to conenct to.
     * @param logPath Where the jersey log will be created and appended.
     */

    public TerminalApiClient(String postUrl, String apiVersion, String user, String pass, String deviceId, String logPath) {
        this.postUrl =postUrl;
        this.apiVersion =apiVersion;
        this.deviceId = deviceId;

        TerminalApiClient.initLogger(logPath);

        Feature logfeature = new LoggingFeature(log, Level.FINE, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192);

        try {
            sslContext.getDefault();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        client = ClientBuilder.newBuilder().build();
        //HttpAuthenticationFeature auth = HttpAuthenticationFeature.universalBuilder()
         //       .credentialsForBasic(user, pass).credentials(user, pass).build();

        HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic(user, pass);

        client.register(auth);
        client.register(logfeature);
        client.register(GZipEncoder.class);

    }

    /**
     *
     * @return The response from the API ping
     */
    public Response postPingNoAuth() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/no-auth/ping").request(MediaType.TEXT_PLAIN);
        return builder.post(Entity.text(""));
    }

    /**
     *
     * @return The response from the API ping
     */
    public Response postPing() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/ping")
                .request( MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);
        return builder.post(Entity.text(""));
    }

    /**
     *
     * @return The response from the API ping
     */
    public Response postByodEnquire() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/byod/enquire")
                .request( MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);
        return builder.post(Entity.entity(new ByodEnquire("sometoken"), MediaType.APPLICATION_JSON));


    }

    /**
     *
     * @return The response from the API ping
     */
    public Response postTotePingNoAuth() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/no-auth/ping/tote").request(MediaType.TEXT_PLAIN);
        return builder.post(Entity.text(""));
    }

    /**
     *
     * @return The response from the API config
     */
    public Response postConfig() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/config")
                .request( MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);
        return builder.post(Entity.text(""));
    }

   /**
     *
     * @return The response from the API config
     */
    public Response postLogin() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/login")
                .request( MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);
        return builder.post(Entity.text(""));
    }

    /**
     *
     * @param wager The wager that you want to place on the tote.
     * @return A response from the tote.
     */
    public Response postWager(Wager wager) {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/bet/place")
                .request(MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);

        return builder.post(Entity.entity(wager, MediaType.APPLICATION_JSON));
    }


    /**
     *
     * @param merchantRef The merchant reference that you want to enquire the ticket for on the tote.
     * @return A response from the tote.
     */
    public Response getWager(String merchantRef) {
        WebTarget target = client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/wager/" + merchantRef )
                .request(MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);

        return builder.get();
    }

    /**
     *
     * @param tsn The merchant reference that you want to enquire the ticket for on the tote.
     * @return A response from the tote.
     */
    public Response getTsn(String tsn) {
        WebTarget target = client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/tsn/" + tsn ).request(MediaType.APPLICATION_JSON);
        return builder.get();
    }

    /**
     *
     * @param merchantRef The merchant reference that you want to cancel the ticket for on the tote.
     * @return A response from the tote.
     */
    public Response deleteWager(String merchantRef) {
        WebTarget target = client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/wager/" + merchantRef )
                .request(MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);

        return builder.delete();
    }

    /**
     *
     * @param tsn The merchant reference that you want to cancel the ticket for on the tote.
     * @return A response from the tote.
     */
    public Response deleteTsn(String tsn) {
        WebTarget target = client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/tsn/" + tsn )
                .request(MediaType.APPLICATION_JSON)
                .header("Device-System-Id",deviceId);

        return builder.delete();
    }

}
