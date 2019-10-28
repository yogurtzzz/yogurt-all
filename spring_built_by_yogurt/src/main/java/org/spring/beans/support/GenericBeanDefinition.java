package org.spring.beans.support;


import org.spring.beans.core.BeanDefinition;
import org.spring.beans.core.di.PropertyValue;
import org.spring.beans.enums.BeanScope;

import java.util.ArrayList;
import java.util.List;

public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String beanClassName;
    private BeanScope scope;
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public GenericBeanDefinition(String id,String beanClassName){
        this.id = id;
        this.beanClassName = beanClassName;
    }
    public String getId() {
        return id;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setScope(BeanScope scope){
        this.scope = scope;
    }
    public BeanScope getScope(){
        return this.scope;
    }
    public boolean isSingleton(){
        return BeanScope.SINGLETON.equals(this.scope);
    }
    public boolean isPrototype(){
        return BeanScope.PROTOTYPE.equals(this.scope);
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }
    public void setPropertyValues(List<PropertyValue> propertyValues){
        this.propertyValues = propertyValues;
    }
}
