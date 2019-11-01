package org.litespring.beans;

import org.litespring.beans.enums.BeanScope;
import org.litespring.core.di.PropertyValue;

import java.util.List;

public interface BeanDefinition {
    String getBeanClassName();
    String getId();
    boolean isSingleton();
    boolean isPrototype();
    BeanScope getScope();
    List<PropertyValue> getPropertyValues();
    void addPropertyValue(PropertyValue propertyValue);
}
