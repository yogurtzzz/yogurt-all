package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionParser;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassLoaderUtils;

import java.io.IOException;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory = null;


    /**
     * TODO 配置完ConfigurableBeanFactory后，有个问题，必须在初始化之后，才能调用setBeanClassLoader
     * TODO 然而，初始化时，在XmlReader解析xml文件获取Resource的时候，就使用了classLoader，此时是绝对没有进行过classLoader的设置的
     * TODO 所以，这样设置classLoader，是有问题的
     */
    private ClassLoader classLoader = null;
    public AbstractApplicationContext(String config) throws IOException {
        beanFactory = new DefaultBeanFactory();
        /**
         * 解析完后的 BeanDefinition 注册到哪  ，先把这个告诉  Xml 解析器
         */
        XmlBeanDefinitionParser parser = new XmlBeanDefinitionParser(beanFactory);
        /**
         * 再执行解析，这一步，每解析并封装好一个BeanDefinition，就会调用注册器的registerBeanDefinition
         */
        parser.loadBeanDefinition(this.getResourceByPath(config));
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }

    public abstract Resource getResourceByPath(String config);

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        this.beanFactory.setBeanClassLoader(this.classLoader);
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader == null ? ClassLoaderUtils.getDefaultClassLoader() : this.classLoader;
    }
}
