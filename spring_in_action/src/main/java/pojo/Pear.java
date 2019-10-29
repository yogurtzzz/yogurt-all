package pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Pear extends Fruit {
    public Pear(){
        System.out.println("新鲜的梨咯");
    }
}
