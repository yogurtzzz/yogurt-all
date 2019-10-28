package org.spring.beans.support;
import org.spring.beans.core.BeanDefinition;
import org.spring.beans.core.BeanDefinitionRegistry;
import org.spring.beans.core.di.PropertyValue;
import org.spring.beans.enums.BeanScope;
import org.spring.beans.exception.BeanCreationException;
import org.spring.beans.factory.ConfigurableBeanFactory;
import org.spring.utils.ClassLoaderUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {
//第一阶段
    //编写BeanFactory实现类，实现简单的加载XML文件，装载BeanDefinition，获取bean实例的功能

//第二阶段重构
    //该接口对外，应该只提供一个getBean，用于获取bean的实例
    //BeanDefinition应是一个内部的概念，不应该提供给外部调用
    //将BeanDefinition从BeanFactory中挪走，新建一个BeanDefinitionRegistry接口,其中提供2个方法
        // getBeanDefinition方法=>暴露给BeanFactory使用（在实例化bean时使用）
        // registerBeanDefinition方法=>暴露给XMLBeanDefinitionParser使用
    //XMLBeanDefinitionParser负责解析XML，封装BeanDefinition，注册BeanDefinition到BeanDefinitionRegistry
    //BeanFactory只负责提供bean实例，只包含一个getBean方法
    //BeanDefinitionRegistry充当中间层,沟通BeanFactory和XMLBeanDefinitionParser
    //DefaultBeanFactory类，同时实现BeanFactory和BeanDefinitionRegistry接口

//第三阶段重构
    //在spring中，一般是见不着BeanFactory的
    //通常是在ApplicationContext中去获取bean，而不用关心底层的实现细节
    //定义一个ApplicationContext接口，继承BeanFactory接口，对外只提供一个getBean方法
    //添加实现类，如ClassPathXmlApplicationContext，其内部持有一个DefaultBeanFactory


//第四阶段重构
    //将ApplicationContext的不同实现类中，将[资源的获取]抽取出来，形成一个Resource接口（内部提供一个getInputStream方法），针对不同的ApplicationContext，实现不同的Resource接口实现来
    //Resource接口的功能是：根据入参的 <文件路径>字符串，获得xml文件的InputStream，后交给dom4j去进行xml解析
        //ClassPathResource中通过ClassLoader对classpath下的资源进行获取
        //FileSystemResource中通过JAVA 的API ：File，来对磁盘下的文件进行获取
    //不同的ApplicationContext实现类，对Resource的获取是不一样的
    //将ClassPathXmlApplicationContext和FileSystemXmlApplicationContext中共同的部分抽取出来
    //形成一个AbstractApplicationContext，使用<模板方法>的设计模式，将[Resource的获取] 设为一个abstract方法，交由具体实现类去实现


//第五阶段重构
    //现有的实现中，ClassLoader的获取是写死的，通常new一个ApplicationContext，只会传入一个String，资源文件路径，此时会使用默认的ClassLoader
    //若要让ClassLoader变成可配置的呢？
    //添加一个ConfigurableBeanFactory，继承BeanFactory接口，新增 <setClassLoader>  <getClassLoader>  两个方法

    //让DefaultBeanFactory和ApplicationContext 去实现/继承这个ConfigurableBeanFactory

//第六阶段
    //对bean添加scope属性
    //新增一个SingletonBeanRegistry接口，提供注册单例bean,获取单例bean的2个方法
    //添加一个SingletonBeanRegistry的默认实现类DefaultSingletonBeanRegistry
    //让DefaultBeanFactory继承这个DefaultSingletonBeanRegistry

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ClassLoader classLoader;

    public Object getBean(String id) {
        //这一步是创建bean实例，期间出现的异常应该为BeanCreationException
        BeanDefinition beanDefinition = this.getBeanDefinition(id);
        if (beanDefinition == null)
            throw new BeanCreationException("BeanDefinition not found");
        BeanScope beanScope = beanDefinition.getScope();
        try {
            Object instance;
            if (BeanScope.SINGLETON.equals(beanScope)){
                instance = this.getSingleton(id);
                if (instance != null)
                    return instance;
                else{
                    instance = this.createBeanInstance(beanDefinition);
                    this.registerSingleton(id,instance);
                    return instance;
                }
            }else{
                return this.createBeanInstance(beanDefinition);
            }

        } catch (Exception e) {
            //将所有异常统一catch住，统一抛出BeanCreationException
            throw new BeanCreationException("create bean for "+beanDefinition.getBeanClassName()+" failed",e);
        }
    }

    private Object createBeanInstance(BeanDefinition beanDefinition){
        ClassLoader cl = this.getClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try{
            Class<?> clazz = cl.loadClass(beanClassName);
            Object instance = clazz.newInstance();
            return instance;
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + "failed",e);
        }
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getId(),beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        if (beanDefinitionMap == null || beanDefinitionMap.size() == 0)
            return null;
        return beanDefinitionMap.get(id);
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return (this.classLoader != null)? this.classLoader : ClassLoaderUtils.getDefaultClassLoader();
    }
}
