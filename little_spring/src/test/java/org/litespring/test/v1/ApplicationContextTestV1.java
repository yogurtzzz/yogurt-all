package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.FruitStoreService;

import java.io.IOException;

public class ApplicationContextTestV1 {


    @Test
    public void testGetBean() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("fruitStore.xml");
        FruitStoreService service = (FruitStoreService) context.getBean("fruitService");
        Assert.assertNotNull(service);
    }

    @Test
    public void testFileSystemContext() throws IOException {
        ApplicationContext context = new FileSystemXmlApplicationContext("G:\\MyCourse\\yogurt-all\\little_spring\\src\\test\\resources\\fruitStore.xml");
        FruitStoreService service = (FruitStoreService) context.getBean("fruitService");
        Assert.assertNotNull(service);
    }
}
