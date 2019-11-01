package org.litespring.service.v2;

import org.litespring.po.v1.Fruit;
public class FruitStoreService {
    private Fruit fruit;
    private String storeName;
    private int dailyProfit;

    public int getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(int dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public void doService(){
        System.out.println("Fruit will be served");
        if (fruit != null){
            System.out.println(fruit.getName() + " tastes " + fruit.getTaste());
        }
    }
}
