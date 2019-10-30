package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource(){
        Resource resource = new ClassPathResource("fruitStore.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFileSystemResource(){
        Resource resource = new FileSystemResource("G:\\MyCourse\\yogurt-all\\little_spring\\src\\test\\resources\\fruitStore.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
