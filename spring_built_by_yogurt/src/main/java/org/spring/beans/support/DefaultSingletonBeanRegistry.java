package org.spring.beans.support;

import org.spring.beans.core.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String,Object> beanSingletonMap = new ConcurrentHashMap<>();
    @Override
    public void registerSingleton(String id,Object instance) {
        beanSingletonMap.put(id,instance);
    }

    @Override
    public Object getSingleton(String id) {
        return beanSingletonMap.get(id);
    }
}
