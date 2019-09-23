package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sound.CD;
import sound.CDPlayer;

//@RunWith(SpringJUnit4ClassRunner.class)
public class XMLTest {
    ApplicationContext context;
    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-demo.xml");
    }

    @Test
    public void test(){
        CD cd = context.getBean(CD.class);
        System.out.println(cd.getBeanName());
        CDPlayer player = context.getBean(CDPlayer.class);
        player.play();
    }
}
