package com.ineda.microtote.client;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Formatter;

public class HmacSha1Signature {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private byte[] data;

    public String toHexString() {
        Formatter formatter = new Formatter();

        for (byte b : data) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public String toBase64() {
        byte[] encodedBytes = Base64.getEncoder().encode(data);
        System.out.println("encodedBytes " + new String(encodedBytes));
        return new String(encodedBytes);
    }


    public HmacSha1Signature calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);

       // return toHexString(mac.doFinal(data.getBytes()));  //If you want a hex string
       // return toBase64(mac.doFinal(data.getBytes()));      //If you want base64
        this.data = mac.doFinal(data.getBytes());
        return this;
    }


}