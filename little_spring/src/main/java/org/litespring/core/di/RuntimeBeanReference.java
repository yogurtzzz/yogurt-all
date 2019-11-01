package org.litespring.core.di;

public class RuntimeBeanReference {
    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    private String beanName;

    public String getBeanName() {
        return beanName;
    }
}
