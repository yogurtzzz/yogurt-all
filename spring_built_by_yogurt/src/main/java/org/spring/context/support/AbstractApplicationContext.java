package org.spring.context.support;

import org.spring.beans.core.io.Resource;
import org.spring.beans.support.DefaultBeanFactory;
import org.spring.beans.xml.XMLBeanDefinitionParser;
import org.spring.context.ApplicationContext;
import org.spring.utils.ClassLoaderUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory;
    private ClassLoader classLoader;
    public AbstractApplicationContext(String configPath){
        beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionParser parser = new XMLBeanDefinitionParser(beanFactory);
        Resource resource = this.getResourceByPath(configPath);
        parser.loadBeanDefinition(resource);
    }
    @Override
    public Object getBean(String id) {
        return beanFactory.getBean(id);
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return (this.classLoader != null) ? this.classLoader : ClassLoaderUtils.getDefaultClassLoader();
    }

    //这个方法交由ClassPathXmlApplicationContext 和 FileSystemXmlApplicationContext 去实现
    public abstract Resource getResourceByPath(String configPath);

}
