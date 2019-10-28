package org.spring.beans.factory;

public interface ConfigurableBeanFactory extends BeanFactory {
    void setClassLoader(ClassLoader classLoader);
    ClassLoader getClassLoader();
}
