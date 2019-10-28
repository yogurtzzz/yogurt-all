package org.spring.utils;

public class ClassLoaderUtils {
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null){
            classLoader = ClassLoaderUtils.class.getClassLoader();
        }
        if (classLoader == null)
            classLoader = ClassLoader.getSystemClassLoader();
        return classLoader;
    }
}
