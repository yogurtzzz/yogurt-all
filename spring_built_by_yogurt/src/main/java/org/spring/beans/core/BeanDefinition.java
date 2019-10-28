package org.spring.beans.core;

import org.spring.beans.core.di.PropertyValue;
import org.spring.beans.enums.BeanScope;

import java.util.List;

public interface BeanDefinition {
    String getId();
    String getBeanClassName();
    BeanScope getScope();
    boolean isSingleton();
    boolean isPrototype();

    List<PropertyValue> getPropertyValues();
}
