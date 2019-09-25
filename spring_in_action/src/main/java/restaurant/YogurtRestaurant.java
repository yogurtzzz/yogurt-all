package restaurant;

import niceFood.Food;
import niceFood.FoodMark;
import niceFood.Hamburger;
import niceFood.Sandwitch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class YogurtRestaurant {

    @Bean
    @Qualifier("healthy")
    public Food smz(){
        return new Sandwitch();
    }

    @Bean
    @Qualifier("fried")
    public Food hbb(){
        return new Hamburger();
    }
}
