package org.litespring.util;

import org.apache.bcel.generic.IF_ACMPEQ;

public class StringUtils {
    public static String capString(String str){
        if (str == null || str.length() == 0)
            return str;
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return new String(cs);
    }

    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str){
        return str != null && str.length() > 0;
    }

    public static boolean isBlank(String str){

        return str == null || str.matches("\\s*");
    }

    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }

}
