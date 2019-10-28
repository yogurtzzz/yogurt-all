package org.spring.utils;

import java.util.Collection;

public class CollectionUtils {
    public static boolean isEmpty(Collection collection){
        if (collection == null || collection.size() == 0)
            return true;
        return false;
    }
}
