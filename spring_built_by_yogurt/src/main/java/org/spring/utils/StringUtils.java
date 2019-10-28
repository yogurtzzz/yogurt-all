package org.spring.utils;

public class StringUtils {
    public static boolean isBlank(String str){
        if (str == null || "".equals(str))
            return true;
        String blankRex = "\\s+";
        return str.matches(blankRex);
    }
}
