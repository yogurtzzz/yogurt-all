import org.junit.Assert;
import org.junit.Test;
import org.spring.context.ApplicationContext;
import org.spring.context.support.ClassPathXmlApplicationContext;
import org.spring.context.support.FileSystemXmlApplicationContext;
import org.spring.service.DinnerService;

public class ApplicationContextTest {

    @Test
    public void testGetBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("restaurant.xml");
        DinnerService dinnerService = (DinnerService) context.getBean("dinnerService");
        Assert.assertNotNull(dinnerService);
        dinnerService.serveDinner();
    }

    @Test
    public void testGetBeanFromFileSystemApplicationContext(){
        ApplicationContext context = new FileSystemXmlApplicationContext("E:\\MyCourse\\org-yogurt-diy\\my-spring\\src\\main\\resources\\restaurant.xml");
        DinnerService dinnerService = (DinnerService) context.getBean("dinnerService");
        Assert.assertNotNull(dinnerService);
        dinnerService.serveDinner();
    }
}
