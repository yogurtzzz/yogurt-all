package org.spring.beans.core.di;

public class TypeStringValue {
    private String value;
    public TypeStringValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
