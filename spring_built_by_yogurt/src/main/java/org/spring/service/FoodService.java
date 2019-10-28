package org.spring.service;

public class FoodService {
    private String foodName;
    private Integer foodPrice;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }
    public void doService(){
        System.out.println("Food named " + foodName + " is served");
    }
}
