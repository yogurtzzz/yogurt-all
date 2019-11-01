package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.BeanPropertyValueResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionParser;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.core.di.*;
import org.litespring.core.io.ClassPathResource;
import org.litespring.po.v1.Orange;
import org.litespring.service.v2.FruitStoreService;

import java.beans.Introspector;
import java.io.IOException;
import java.util.List;

public class ApplicationContextTestV2 {
    @Test
    public void testSetterInjection() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("fruitStore-v2.xml");
        FruitStoreService storeService = (FruitStoreService) context.getBean("fruitService");
        String storeName = storeService.getStoreName();
        Assert.assertNotNull(storeService);
        Assert.assertNotNull(storeService.getFruit());
        Assert.assertEquals("yogurtHappyFruit",storeName);
        storeService.doService();
    }

    @Test
    public void testGetBeanDefinition() throws IOException {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionParser parser = new XmlBeanDefinitionParser(beanFactory);
        parser.loadBeanDefinition(new ClassPathResource("fruitStore-v2.xml"));

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("fruitService");
        Assert.assertNotNull(beanDefinition);

        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        Assert.assertEquals(2,propertyValues.size());
        PropertyValue pv = this.getPropertyValue("fruit",propertyValues);
        Assert.assertNotNull(pv);
        Assert.assertTrue(pv.getPropertyValue() instanceof RuntimeBeanReference);

        PropertyValue pv2 = this.getPropertyValue("storeName",propertyValues);
        Assert.assertNotNull(pv2);
        Assert.assertTrue(pv2.getPropertyValue() instanceof TypedStringValue);
    }

    private PropertyValue getPropertyValue(String propertyName,List<PropertyValue> pvs){
        for (PropertyValue pv : pvs){
            if (pv.getPropertyName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }

    @Test
    public void testValueResolver() throws IOException {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionParser parser = new XmlBeanDefinitionParser(beanFactory);
        parser.loadBeanDefinition(new ClassPathResource("fruitStore-v2.xml"));

        BeanPropertyValueResolver resolver = new BeanPropertyValueResolver(beanFactory);
        RuntimeBeanReference ref = new RuntimeBeanReference("orange");
        TypedStringValue value = new TypedStringValue("good");

        Object refObject = resolver.resolveValueIfNecessary(ref,null);
        Object stringObject = resolver.resolveValueIfNecessary(value,String.class);

        Assert.assertNotNull(refObject);

        Assert.assertNotNull(stringObject);

        Assert.assertTrue(refObject instanceof Orange);
        Assert.assertTrue(stringObject instanceof String);
    }


    @Test
    public void testTypeConverter(){
        TypeConverter typeConverter = new NumberConverter();
        String strInt = "20000";
        Object obj = typeConverter.convert(strInt,Integer.class);
        Assert.assertTrue(obj instanceof Integer);
        Assert.assertEquals(20000,obj);
    }
}
