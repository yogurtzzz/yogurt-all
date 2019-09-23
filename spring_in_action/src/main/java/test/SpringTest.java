package test;

import niceFood.Food;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sound.CDConfig;
import sound.CDPlayer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDConfig.class)
public class SpringTest {

    @Autowired
    private CDPlayer cdPlayer;

    @Autowired(required = false)
    private Food food;

    @Test
    public void play(){
        cdPlayer.play();
        System.out.println(cdPlayer.getName());
        System.out.println(food.getName());
    }
}
