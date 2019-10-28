package org.spring.service;

public class DinnerService {
    private FoodService foodService;
    private DrinkService drinkService;
    public void serveDinner() {
        System.out.println("This is KFC serving dinner.");
    }

    public FoodService getFoodService() {
        return foodService;
    }

    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    public DrinkService getDrinkService() {
        return drinkService;
    }

    public void setDrinkService(DrinkService drinkService) {
        this.drinkService = drinkService;
    }
}
