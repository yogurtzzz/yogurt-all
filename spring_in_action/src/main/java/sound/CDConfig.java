package sound;

import niceFood.Food;
import niceFood.Sandwitch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDConfig {

    @Bean
    public Food sandwitch(){
        return new Sandwitch();
    }

    @Bean
    public CDPlayer jayPlayer(CD cd, Food food){
        return new JayCDPlayer(cd,food);
    }

    @Bean
    public CD jayCD(){
        return new JayCD();
    }
}
