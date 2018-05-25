package com.indea.microtote;

import com.ineda.microtote.client.HmacSha1Signature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HmacSha1Test {
    @Test
    public void A_givenKnownDataAndKey_onEqualsResult_goodEncoding() {
        String hmac = null;
        try {
            hmac = new HmacSha1Signature()
                    .calculateRFC2104HMAC("data", "key")
                    .toHexString();

        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        System.out.println(hmac);
        assert hmac.equals("104152c5bfdca07bc633eebd46199f0255c9f49d");
    }


    @Test
    public void B_givenColosusPostData_onEqualsColosusGivenData_goodEncoding() {
        String hmac = null;
        try {
            hmac = new HmacSha1Signature()
                    .calculateRFC2104HMAC("POST,application/json,rY5GyingAbnOReyXXtSqyA==,/tickets,Tue, 11 Apr 2017 16:05:42 GMT", "api_secret")
                    .toBase64();

        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        assert hmac.equals("QZDwWjZOGKCe+DSVWwAQGhKySQ0=");
    }

}
