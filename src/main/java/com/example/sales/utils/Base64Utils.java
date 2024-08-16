package com.example.sales.utils;

import java.util.Base64;

public class Base64Utils {
    public static byte[] base64ToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    public static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
