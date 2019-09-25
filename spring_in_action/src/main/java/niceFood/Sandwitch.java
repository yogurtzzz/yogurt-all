package niceFood;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


public class Sandwitch implements Food {
    public Sandwitch(){
        System.out.println("Sandwitch initializing..");
    }
    @Override
    public String getName() {
        return "三明治";
    }

}
