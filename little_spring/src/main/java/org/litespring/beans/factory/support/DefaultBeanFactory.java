package org.litespring.beans.factory.support;

import org.apache.bcel.generic.INSTANCEOF;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.enums.BeanScope;
import org.litespring.beans.exception.BeanInstantiationException;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.core.di.PropertyValue;
import org.litespring.core.di.RuntimeBeanReference;
import org.litespring.core.di.TypedStringValue;
import org.litespring.util.ClassLoaderUtils;
import org.litespring.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    private BeanPropertyValueResolver resolver;

    //ConfigurableBeanFactory接口
    private ClassLoader classLoader;

    //BeanDefinitionRegister接口
    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }
    @Override
    public void registerBeanDefinition(String id, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id,beanDefinition);
    }

    //BeanFactory接口
    @Override
    public Object getBean(String beanId) {
        //bean的实例化放在这里进行
        BeanDefinition beanDefinition = this.getBeanDefinition(beanId);
        if (beanDefinition == null){
            throw new BeanInstantiationException("BeanDefinition for beanId = " + beanId + " does not exists");
        }
        if (beanDefinition.isSingleton()){
            //如果是单例
            Object oldBean = this.getSingleton(beanId);
            if (oldBean == null){
                oldBean = createBean(beanDefinition);
                this.registerSingleton(beanId,oldBean);
            }
            return oldBean;
        }else{
            return createBean(beanDefinition);
        }
    }

    private Object createBean(BeanDefinition beanDefinition){
        //bean实例化
        Object bean = beanInstantiation(beanDefinition);
        //setter 依赖注入
        try {
            loadProperty(beanDefinition,bean);
        } catch (Exception e) {
            throw new BeanInstantiationException(e);
        }
        return bean;
    }
    private Object beanInstantiation(BeanDefinition beanDefinition){
        String beanClassName = beanDefinition.getBeanClassName();
        String beanId = beanDefinition.getId();
        ClassLoader classLoader = this.getBeanClassLoader();
        try {
            Class<?> clzz = classLoader.loadClass(beanClassName);
            Object instance = clzz.newInstance();
            return instance;
        } catch (Exception e) {
            throw new BeanInstantiationException("Create bean for " + beanClassName +" failed",e);
        }
    }
    private void loadProperty(BeanDefinition beanDefinition,Object instance) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        List<PropertyValue> pvs = beanDefinition.getPropertyValues();
        Class<?> clzz = instance.getClass();
        if (pvs == null || pvs.isEmpty())
            return;
        /**
         * 进行setter注入
         */
        for (PropertyValue pv : pvs) {
            String propertyName = pv.getPropertyName();
            Object originalValue = pv.getPropertyValue();
            BeanPropertyValueResolver valueResolver = new BeanPropertyValueResolver(this);

            BeanInfo beanInfo = Introspector.getBeanInfo(clzz);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors){
                if (descriptor.getName().equals(propertyName)){
                    Class<?> paramClzz = descriptor.getPropertyType();
                    Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue,paramClzz);
                    descriptor.getWriteMethod().invoke(instance,resolvedValue);
                    break;
                }
            }
        }

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader == null ? ClassLoaderUtils.getDefaultClassLoader() : this.classLoader;
    }
}
