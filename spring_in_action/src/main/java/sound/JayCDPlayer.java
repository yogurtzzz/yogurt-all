package sound;

import niceFood.Food;
import org.springframework.beans.factory.BeanNameAware;

public class JayCDPlayer implements CDPlayer, BeanNameAware {
    private CD cd;
    private Food food;
    private int price;
    private String name;
    public void play() {
        System.out.println("playing " + cd.getAuthor() + "'s " + cd.getCDName() + "beanName = " + cd.getBeanName());
    }


    public JayCDPlayer(){

    }
    public JayCDPlayer(CD cd, Food food){
        System.out.println("start to inject " + cd.getBeanName()+" " + food.getName());
        this.cd = cd;
        this.food = food;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setBeanName(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
