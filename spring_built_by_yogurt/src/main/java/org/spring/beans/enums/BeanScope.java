package org.spring.beans.enums;

public enum BeanScope {
    SINGLETON("singleton"),
    PROTOTYPE("prototype");

    private String scope;
    BeanScope(String scope){
        this.scope = scope;
    }
    public String getScope(){
        return this.scope;
    }
    public boolean equals(BeanScope another){
        return this.getScope().equals(another.getScope());
    }
}
