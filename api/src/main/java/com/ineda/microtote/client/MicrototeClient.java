package com.ineda.microtote.client;

import com.ineda.microtote.models.Wager;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.net.ssl.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MicrototeClient {
    private static final Logger log = Logger.getLogger( MicrototeClient.class.getName() );
    private static FileHandler fh = null;

    String postUrl;
	String apiVersion;
	String keysPath = "";
	String keystorePassword;
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
     * @param keyStorePath Where the keytstore is located on the server.
     * @param trustStorePath Where the truststore is located on the server.
     * @param keystorePassword The password setup for the keyStores you created using keytool commands.
     * @param logPath Where the jersey log will be created and appended.
     */

    public MicrototeClient(String postUrl, String apiVersion, String keyStorePath, String trustStorePath, String keystorePassword, String logPath) {
        this.postUrl =postUrl;
        this.apiVersion =apiVersion;
        this.keystorePassword = keystorePassword;

        MicrototeClient.initLogger(logPath);

        log.log(Level.FINE, "KEYSTORE path = " + keyStorePath + "\n" );
        log.log(Level.FINE, "TRUSTSTORE path = " + trustStorePath + "\n");

        Feature logfeature = new LoggingFeature(log, Level.FINE, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192);

        KeyStores ks = new KeyStores(trustStorePath, keyStorePath, keystorePassword);
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(ks.getKmf().getKeyManagers(), ks.getTmf().getTrustManagers(), null); // the final null is for default secure random source
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.FINE, "KEYSTORE Algorithm exception " + e.getMessage() + "\n" + e.getCause() + e.getStackTrace() );
        } catch (KeyManagementException e) {
            log.log(Level.FINE, "KEYSTORE Key Management exception" + e.getMessage() + "\n" + e.getCause() + e.getStackTrace() );
        }

        client = ClientBuilder.newBuilder().sslContext(sslContext).build();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
                .credentialsForBasic("", "").credentials("", "").build();
        client.register(feature);
        client.register(logfeature);
    }

    /**
     *
     * @return The response from the API ping
     */
    public Response getPing() {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/ping/").request(MediaType.APPLICATION_JSON);
        return builder.get();
    }

    /**
     *
     * @param wager The wager that you want to place on the tote.
     * @return A response from the tote.
     */
    public Response postWager(Wager wager) {
        WebTarget target	= client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/wager/" + wager.getMerchantReference() ).request(MediaType.APPLICATION_JSON);
        return builder.post(Entity.entity(wager, MediaType.APPLICATION_JSON));
    }


    /**
     *
     * @param merchantRef The merchant reference that you want to enquire the ticket for on the tote.
     * @return A response from the tote.
     */
    public Response getWager(String merchantRef) {
        WebTarget target = client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/wager/" + merchantRef ).request(MediaType.APPLICATION_JSON);
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
        Invocation.Builder builder = target.path(apiVersion+"/wager/" + merchantRef ).request(MediaType.APPLICATION_JSON);
        return builder.delete();
    }

    /**
     *
     * @param tsn The merchant reference that you want to cancel the ticket for on the tote.
     * @return A response from the tote.
     */
    public Response deleteTsn(String tsn) {
        WebTarget target = client.target(postUrl);
        Invocation.Builder builder = target.path(apiVersion+"/tsn/" + tsn ).request(MediaType.APPLICATION_JSON);
        return builder.delete();
    }

}
