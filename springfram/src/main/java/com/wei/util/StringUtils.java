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

    public static String replaceFirstCase(String defaultName) {
        String firstWord = defaultName.substring(0, 1);
        String lowWord = firstWord.toLowerCase();
        defaultName = defaultName.replaceFirst(firstWord, lowWord);
        return defaultName;
    }
}
