package org.spring.beans.core;

import org.spring.beans.core.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String id);
}
