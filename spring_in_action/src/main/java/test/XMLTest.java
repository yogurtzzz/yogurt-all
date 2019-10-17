package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
public class XMLTest {
    ApplicationContext context;
    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-demo.xml");
    }

    @Test
    public void test(){
        Map<Integer,Integer> map = new HashMap<>();
        int a = 10;
        for (int i = 0; i < 10000; i++){
            int rand = (int)(Math.random() * a);
            if (map.containsKey(rand)){
                int count = map.get(rand);
                map.put(rand,count+1);
            }else{
                map.put(rand,1);
            }
        }
        boolean flag = true;
        for (int i = 0; i < a;i++){
            flag = flag && map.containsKey(i);
        }
        System.out.println(flag);
    }
}
