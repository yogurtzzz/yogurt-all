package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.enums.BeanScope;
import org.litespring.beans.exception.BeanDefinitionParseException;
import org.litespring.beans.exception.BeanInstantiationException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionParser;
import org.litespring.core.io.ClassPathResource;
import org.litespring.po.v1.Fruit;
import org.litespring.service.v1.FruitStoreService;

import java.io.IOException;

public class BeanFactoryTest {
    private static final String fruitStoreXml = "fruitStore.xml";
    private static final String hasSomeErrorXml = "invalid.xml";
    private static final String nonExistXml = "xxx.xml";
    private DefaultBeanFactory beanFactory;
    private XmlBeanDefinitionParser parser;

    @Before
    public void init(){
        beanFactory = new DefaultBeanFactory();
        parser = new XmlBeanDefinitionParser(beanFactory);
    }

    @Test
    public void testGetBean() throws IOException {
        parser.loadBeanDefinition(new ClassPathResource(fruitStoreXml));
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("fruitService");

        Assert.assertEquals("org.litespring.service.v1.FruitStoreService",beanDefinition.getBeanClassName());

        FruitStoreService fruitStoreService = (FruitStoreService) beanFactory.getBean("fruitService");

        Assert.assertNotNull(fruitStoreService);
        fruitStoreService.doService();
    }

    //测试获取无效的bean，测试BeanInstantiationException
    @Test(expected = BeanInstantiationException.class)
    public void testInvalidBean() throws IOException {
        parser.loadBeanDefinition(new ClassPathResource(fruitStoreXml));
        Object obj = beanFactory.getBean("invalid");
    }

    //无效的XML文件或者XML文件内容有误
    @Test(expected = BeanDefinitionParseException.class)
    public void testInvalidXML() throws IOException {
        parser.loadBeanDefinition(new ClassPathResource(nonExistXml));
    }

    @Test(expected = BeanDefinitionParseException.class)
    public void testInvalidXML2() throws IOException {
        parser.loadBeanDefinition(new ClassPathResource(hasSomeErrorXml));
    }

    @Test
    public void testSingleton() throws IOException {
        parser.loadBeanDefinition(new ClassPathResource(fruitStoreXml));
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("fruitService");

        Assert.assertTrue(beanDefinition.isSingleton());
        Assert.assertFalse(beanDefinition.isPrototype());
        Assert.assertEquals(BeanScope.SINGLETON,beanDefinition.getScope());

        FruitStoreService service1 = (FruitStoreService) beanFactory.getBean("fruitService");
        Assert.assertNotNull(service1);
        FruitStoreService service2 = (FruitStoreService) beanFactory.getBean("fruitService");
        Assert.assertEquals(service1,service2);
    }

    @Test
    public void testPrototype() throws IOException {
        parser.loadBeanDefinition(new ClassPathResource(fruitStoreXml));
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("fruitServicePro");

        Assert.assertFalse(beanDefinition.isSingleton());
        Assert.assertTrue(beanDefinition.isPrototype());
        Assert.assertEquals(BeanScope.PROTOTYPE,beanDefinition.getScope());

        FruitStoreService service1 = (FruitStoreService) beanFactory.getBean("fruitServicePro");
        Assert.assertNotNull(service1);
        FruitStoreService service2 = (FruitStoreService) beanFactory.getBean("fruitServicePro");
        Assert.assertNotEquals(service1,service2);
    }
}
