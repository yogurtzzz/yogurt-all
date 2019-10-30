package org.litespring.po.v1;

public class Orange implements Fruit {
    @Override
    public String getName() {
        return "Orange";
    }

    @Override
    public String getTaste() {
        return "sweet and a little sour";
    }
}
