package com.yogurt.beanFactory;

import java.util.HashMap;
import java.util.Map;

public class YogurtBeanDefinition {
    /**
     * bean definition
     * **/
    private String id;
    private String beanName;
    private String beanClassName;
    private String initMethod;
    private Map<String,Object> propertyValues = new HashMap<>();

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public Map<String, Object> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, Object> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
