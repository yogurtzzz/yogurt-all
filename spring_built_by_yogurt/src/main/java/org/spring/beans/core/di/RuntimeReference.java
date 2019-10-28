package org.spring.beans.core.di;

public class RuntimeReference {
    private String beanName;

    public RuntimeReference(String beanName){
        this.beanName = beanName;
    }
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
