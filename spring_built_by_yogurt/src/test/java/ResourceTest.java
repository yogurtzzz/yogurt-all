import org.junit.Assert;
import org.junit.Test;
import org.spring.beans.core.io.ClassPathResource;
import org.spring.beans.core.io.FileSystemResource;
import org.spring.beans.core.io.Resource;
import org.spring.beans.enums.BeanScope;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource() throws FileNotFoundException {
        Resource resource = new ClassPathResource("restaurant.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }

    @Test
    public void testFileSystemResource() throws FileNotFoundException {
        Resource resource = new FileSystemResource("E:\\MyCourse\\org-yogurt-diy\\my-spring\\src\\main\\resources\\restaurant.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }

}
