import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.spring.beans.core.BeanDefinition;
import org.spring.beans.core.io.ClassPathResource;
import org.spring.beans.enums.BeanScope;
import org.spring.beans.exception.BeanCreationException;
import org.spring.beans.exception.BeanDefinitionException;
import org.spring.beans.support.DefaultBeanFactory;
import org.spring.beans.xml.XMLBeanDefinitionParser;
import org.spring.service.DinnerService;

import java.io.*;

//TDD  Test Driven Development 测试驱动开发

public class BeanFactoryTest {

    private DefaultBeanFactory beanFactory;
    private XMLBeanDefinitionParser parser;


    //每运行一个测试用例之前，都会重新运行@Before方法
    //每个测试用例之间是隔离的，互不影响的
    @Before
    public void init(){
        beanFactory = new DefaultBeanFactory();
        parser = new XMLBeanDefinitionParser(beanFactory);
    }

    @Test
    public void testGetBean(){
        //测试正常情况，获取bean
        parser.loadBeanDefinition(new ClassPathResource("restaurant.xml"));
        DinnerService dinnerService = (DinnerService) beanFactory.getBean("dinnerService");
        Assert.assertNotNull(dinnerService);
        dinnerService.serveDinner();
    }

    @Test
    public void testInvalidBean(){
        //bean创建过程出错
        parser.loadBeanDefinition(new ClassPathResource("restaurant.xml"));
        try{
            beanFactory.getBean("invalid");
        }catch (BeanCreationException e){
            return ;
        }
        Assert.fail("Expect BeanCreationException");
    }
    @Test
    public void testInvalidXML(){
        //解析XML，封装BeanDefinition时出错
        try {
            parser.loadBeanDefinition(new ClassPathResource("zz.xml"));
        }catch (BeanDefinitionException e){
            return ;
        }
        Assert.fail("Expect BeanDefinitionException");
    }

    @Test
    public void testSingleton(){
        parser.loadBeanDefinition(new ClassPathResource("restaurant.xml"));
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("dinnerService");
        Assert.assertTrue(beanDefinition.isSingleton());
        Assert.assertFalse(beanDefinition.isPrototype());

        DinnerService dinnerService = (DinnerService) beanFactory.getBean("dinnerService");
        Assert.assertNotNull(dinnerService);
        dinnerService.serveDinner();

        DinnerService second = (DinnerService) beanFactory.getBean("dinnerService");
        Assert.assertEquals(dinnerService,second);
    }
}
