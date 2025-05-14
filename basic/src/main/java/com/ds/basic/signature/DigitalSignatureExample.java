package com.ds.basic.signature;

import java.security.MessageDigest;

/**
 * @author ds
 * @date 2025/5/9
 * @description
 */
public class DigitalSignatureExample {
    public static void main(String[] args) throws Exception {
        System.out.println(getMD5Hex("sq123213"));
    }

    public static String getMD5Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
