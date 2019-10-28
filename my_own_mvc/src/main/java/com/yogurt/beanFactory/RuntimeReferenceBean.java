package com.yogurt.beanFactory;

public class RuntimeReferenceBean {
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public RuntimeReferenceBean(String beanName) {
        this.beanName = beanName;
    }
}
