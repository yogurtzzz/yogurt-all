package org.litespring.beans;

import org.litespring.beans.enums.BeanScope;

public interface BeanDefinition {
    String getBeanClassName();
    String getId();

    boolean isSingleton();

    boolean isPrototype();

    BeanScope getScope();
}
