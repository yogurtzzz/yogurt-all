package org.litespring.beans.factory;

public interface SingletonBeanRegistry {
    void registerSingleton(String beanId,Object object);
    Object getSingleton(String beanId);
}
