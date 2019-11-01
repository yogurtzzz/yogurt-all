package org.litespring.po.v1;

public class Watermelon implements Fruit {
    @Override
    public String getName() {
        return "watermelon";
    }

    @Override
    public String getTaste() {
        return "sweet and juicy";
    }
}
