package org.litespring.service.v1;

import org.litespring.po.v1.Fruit;

import java.util.List;

public class FruitStoreService {
    private List<Fruit> fruits;

    public FruitStoreService(List<Fruit> fruits){
        this.fruits = fruits;
    }
    public FruitStoreService(){
    }

    public void doService(){
        System.out.println("Fruits will be served");
        if (fruits == null || fruits.size() == 0)
            return ;
        for (Fruit fruit : fruits){
            System.out.println(fruit.getName() + " " + fruit.getTaste());
        }
    }
}
