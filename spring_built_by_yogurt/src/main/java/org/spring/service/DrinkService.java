package org.spring.service;

public class DrinkService {
    private String drinkName;
    private Integer drinkPrice;

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Integer getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(Integer drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    public void doService(){
        System.out.println("Drink named " + drinkName + " is served");
    }
}
