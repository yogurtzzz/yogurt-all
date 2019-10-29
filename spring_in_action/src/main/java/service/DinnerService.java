package service;

import anno.AdminOnly;
import anno.CheckThis;
import anno.Yogurt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class DinnerService implements SomeService {
    @Override
    public void doService() {
        System.out.println("Serve dinner");
    }

    @Override
    public void doService(Args args) {
        System.out.println("Dinner with args");
    }

    @Override
    public void doService(Long l) {
        System.out.println("Dinner with long "+l);
    }

}
