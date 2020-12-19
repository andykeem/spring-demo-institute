package com.example.demo.util;

public class StringUtil {

    public static boolean isBlank(String text) {
        return (text == null || text.trim().length() < 1);
    }
}
