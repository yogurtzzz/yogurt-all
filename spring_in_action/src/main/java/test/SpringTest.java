package test;

import aop.NewFunction;
import config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.FruitPlate;
import service.Args;
import service.DinnerService;
import service.SomeService;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class SpringTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void test(){
//        assertNotNull(services);
//        for (SomeService service : services){
//            service.doService((long)(Math.random() * 100));
//        }
        SomeService dinnerService = (SomeService) context.getBean("dinnerService");
        assertNotNull(dinnerService);
        dinnerService.doService(1L);

        NewFunction function = (NewFunction) dinnerService;
        function.fun();
    }

    @Test
    public void testLookup() throws InterruptedException {
        Environment environment = context.getEnvironment();
        System.out.println(environment.getProperty("db.connection.count"));
        FruitPlate plate = (FruitPlate) context.getBean(FruitPlate.class);
        plate.serveFruits();
//        Thread.sleep(100);
//        plate.serveFruits();
//        Thread.sleep(100);
//        plate.serveFruits();
    }
}
