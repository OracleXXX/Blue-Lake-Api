package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

@UtilityClass
public class SHA256Util {

    private static final String HMAC_SHA256_ALG = "HmacSHA256";

    public static byte[] hmacSha256(byte[] input, byte[] key) throws GeneralSecurityException {
        SecretKey secretKey = new SecretKeySpec(key, HMAC_SHA256_ALG);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALG);
        mac.init(secretKey);
        return mac.doFinal(input);
    }

    public static String hmacSha256String(byte[] input, byte[] key) throws GeneralSecurityException {
        SecretKey secretKey = new SecretKeySpec(key, HMAC_SHA256_ALG);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALG);
        mac.init(secretKey);
        return byteArrayToLowerString(mac.doFinal(input));
    }

    public static String byteArrayToLowerString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

}