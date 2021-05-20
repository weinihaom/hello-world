package com.wei.util;

public class StringUtils {
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        } else if (str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
