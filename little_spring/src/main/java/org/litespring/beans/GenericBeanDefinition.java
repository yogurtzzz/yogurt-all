package org.litespring.beans;

import org.litespring.beans.enums.BeanScope;
import org.litespring.core.di.PropertyValue;
import org.litespring.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;
    private BeanScope scope;
    private List<PropertyValue> propertyValues = null;
    public GenericBeanDefinition(String id,String className){
        this.id = id;
        this.className = className;
        this.scope = BeanScope.SINGLETON;
    }
    public GenericBeanDefinition(String id,String className,String scope){
        this.id = id;
        this.className = className;
        if (scope == null || StringUtils.isBlank(scope)){
            this.scope = BeanScope.SINGLETON;
        }else{
            this.scope = BeanScope.valueOf(scope.toUpperCase());
        }
    }

    public void addPropertyValue(PropertyValue pv){
        if (this.propertyValues == null){
            this.propertyValues = new ArrayList<>();
        }
        this.propertyValues.add(pv);
    }
    @Override
    public String getBeanClassName() {
        return this.className;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isSingleton() {
        return this.scope.equals(BeanScope.SINGLETON);
    }

    @Override
    public boolean isPrototype() {
        return this.scope.equals(BeanScope.PROTOTYPE);
    }

    @Override
    public BeanScope getScope() {
        return this.scope;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    public void setScope(String scope){
        this.scope = BeanScope.valueOf(scope);
    }
}
