package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("properties/env.properties")
@Component
public abstract class FruitPlate {
    //在单例bean中注入非单例的bean
    //若希望每次使用都是新的bean
    //使用lookup  方法注入
    private Apple apple;

    private Pear pear;

    public void serveFruits(){
        apple = getApple();
        pear = getPear();
        System.out.println("请你吃苹果" + apple);
        System.out.println("请你吃梨" + pear);
    }

    @Lookup
    public abstract Apple getApple();

    @Lookup
    public abstract Pear getPear();
}
