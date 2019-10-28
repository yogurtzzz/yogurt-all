package org.spring.beans.core;

public interface SingletonBeanRegistry {
    void registerSingleton(String id, Object instance);
    Object getSingleton(String id);
}
