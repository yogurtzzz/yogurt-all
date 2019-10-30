package org.litespring.beans.enums;

public enum BeanScope {
    SINGLETON("singleton"),PROTOTYPE("prototype");
    private String desc;
    BeanScope(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
