package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String id, BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String id);
}
