package test;

import niceFood.Food;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restaurant.YogurtRestaurant;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = YogurtRestaurant.class)
public class SpringTest {

    @Autowired
    @Qualifier("healthy")
    private Food food;

    @Test
    public void dining(){
        assertNotNull(food);
        System.out.println(food.getName());
    }
}
