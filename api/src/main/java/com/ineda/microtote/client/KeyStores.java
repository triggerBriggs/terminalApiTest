package com.ineda.microtote.client;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class KeyStores {
    private TrustManagerFactory tmf;
    private KeyManagerFactory kmf;
    private String trustStore;
    private String keyStore;
    private String keyStorePassword;

    /**
     *
     * @param trustStore The path and filename where the trustStore (.jks ) files are located.
     * @param keyStore The path and filename where the keyStore (.jks ) files are located.
     * @param keystorePassword The password setup when generating the .jks files.
     */

    public KeyStores(String trustStore, String keyStore, String keystorePassword) {
        this.trustStore = trustStore;
        this.keyStore = keyStore;
        this.keyStorePassword = keystorePassword;
        loadKeystores();
    }

    /**
     *-cert-key
     */
    public void loadKeystores(){
        try {
            KeyStore jks=KeyStore.getInstance("JKS");
            jks.load(new FileInputStream(trustStore),keyStorePassword.toCharArray());

            tmf = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
            tmf.init(jks);
            X509TrustManager x509TrustManager = null;
            for (TrustManager tm : tmf.getTrustManagers()) {
                if (tm instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) tm;
                    break;
                }
            }

            if (x509TrustManager == null) {
                throw new NullPointerException();
            }

            jks=KeyStore.getInstance("JKS");
            jks.load(new FileInputStream(keyStore),keyStorePassword.toCharArray());

            kmf = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            kmf.init(jks,keyStorePassword.toCharArray());
            X509KeyManager x509KeyManager = null;
            for (KeyManager km : kmf.getKeyManagers()) {
                if (km instanceof X509KeyManager) {
                    x509KeyManager = (X509KeyManager) km;
                    break;
                }
            }

            if (x509KeyManager == null) {
                throw new NullPointerException();
            }
        }catch(Exception e)
        {
            System.out.println("KEYSTORE Error : " + e.getMessage()+ e.getCause());
        }
    }

    /**
     * A helper method in case you are struggling with certificate authentications. It just prints out a bit about the
     * certs in the keystore.
     * I used this during debug so it might help you if you are getting no where.
     * @param jks
     * @throws KeyStoreException
     */
    public void printKeyStoreDetails(KeyStore jks) throws KeyStoreException {
        for (Enumeration<String> t = jks.aliases(); t.hasMoreElements();)
        {
            String alias = t.nextElement();
            System.out.println("\nNext Element : " + alias);
            if (jks.isCertificateEntry(alias)){
                System.out.println("  Certificate found :" + alias);

                Certificate a = jks.getCertificate(alias);
                X509Certificate x509 = (X509Certificate)a;
                System.out.println("     "+ x509.getSubjectDN().toString());

                jks.setCertificateEntry(x509.getSubjectDN().toString(), x509);
                System.out.println("     "+ jks.getCertificateAlias(x509));
                System.out.println("     ok");
            }

            if (jks.isKeyEntry(alias)){
                System.out.println("  Key found :" + alias);

                Certificate[] a = jks.getCertificateChain(alias);
                for (int i=0;i<a.length;i++)
                {
                    X509Certificate x509 = (X509Certificate)a[i];
                    System.out.println("     "+ x509.getSubjectDN().toString());
                    if (i>0)
                        jks.setCertificateEntry(x509.getSubjectDN().toString(), x509);
                    System.out.println("     "+ jks.getCertificateAlias(x509));
                    System.out.println("     ok");
                }
            }
        }
    }

    public TrustManagerFactory getTmf() {
        return tmf;
    }

    public KeyManagerFactory getKmf() {
        return kmf;
    }
}