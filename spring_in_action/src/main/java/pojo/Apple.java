package pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class Apple extends Fruit implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    public Apple(){
        System.out.println("新鲜的苹果咯");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory aware " + beanFactory);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("beanName aware + " + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application Ctx aware " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean..");
    }

    @PreDestroy
    public void aVoid(){
        System.out.println("PreDestroy");
    }

    @PostConstruct
    public void f(){
        System.out.println("PostConstruct");
    }
}
