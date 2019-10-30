package org.litespring.beans.factory.support;

import org.litespring.beans.factory.SingletonBeanRegistry;
import org.litespring.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonMap = new ConcurrentHashMap<>();
    @Override
    public void registerSingleton(String beanId, Object object) {
        Assert.assertNotNull(beanId,"beanId can not be null");
        if (singletonMap.get(beanId) != null){
            throw new IllegalStateException("Could not register bean [" + object + "] with beanId = " + beanId + ", there is already an object with the same beanId");
        }
        singletonMap.put(beanId,object);
    }

    @Override
    public Object getSingleton(String beanId) {
        return singletonMap.get(beanId);
    }
}
