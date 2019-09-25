package niceFood;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


public class Hamburger implements Food{

    public Hamburger(){
        System.out.println("Hamburger initializing...");
    }
    @Override
    public String getName() {
        return "汉堡包";
    }
}
