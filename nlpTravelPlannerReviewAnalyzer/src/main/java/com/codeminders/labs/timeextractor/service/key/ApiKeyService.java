package com.codeminders.labs.timeextractor.service.key;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <h1>ApiKey Service Class</h1> is designed for generation of API key
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-30
 */

public class ApiKeyService {
    private static final String secret = "8484q7af0-5f73-11e4-9803-0800s200c9a66";

    public String generateAPIKey(String email) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        md.update((email + secret).getBytes());
        byte[] shaDig = md.digest();
        return bytesToHex(shaDig);
    }

    public boolean checkKey(String email, String key) {
        if (email == null || key == null) {
            return false;
        }
        String realKey = generateAPIKey(email);
        return key.equals(realKey);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes)
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

}
