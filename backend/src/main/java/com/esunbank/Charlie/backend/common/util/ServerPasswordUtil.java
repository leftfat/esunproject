package com.esunbank.Charlie.backend.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServerPasswordUtil {

    private static final String SALT = "Xa3wNkfjPlp/KzYe9aAqYQ=="; // 盐值，与客户端相同

    public static String encrypt(String password) {
        try {
            //
            String combinedPassword = password + SALT;

            //
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combinedPassword.getBytes());

            //
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validatePassword(String inputPassword, String storedPassword) {
        String hashedInputPassword = encrypt(inputPassword);
        return hashedInputPassword.equals(storedPassword);
    }
}
