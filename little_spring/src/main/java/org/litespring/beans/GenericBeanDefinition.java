package org.litespring.beans;

import org.apache.commons.lang3.StringUtils;
import org.litespring.beans.enums.BeanScope;

public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;
    private BeanScope scope;
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

    public void setScope(String scope){
        this.scope = BeanScope.valueOf(scope);
    }
}
