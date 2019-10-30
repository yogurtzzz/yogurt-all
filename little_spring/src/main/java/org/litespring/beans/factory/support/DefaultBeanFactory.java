package org.litespring.beans.factory.support;

import org.apache.bcel.generic.INSTANCEOF;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.enums.BeanScope;
import org.litespring.beans.exception.BeanInstantiationException;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassLoaderUtils;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

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

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader == null ? ClassLoaderUtils.getDefaultClassLoader() : this.classLoader;
    }
}
