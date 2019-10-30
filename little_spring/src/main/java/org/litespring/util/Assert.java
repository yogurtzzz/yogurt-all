package org.litespring.util;

public class Assert {
    public static void assertNotNull(Object object,String msg){
        if (object == null)
            throw new IllegalArgumentException(msg);
    }
}
