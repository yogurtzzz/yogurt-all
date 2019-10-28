package org.spring.beans.core.di;

public class PropertyValue {
    private String name;
    private Object value;
    private Object convertedValue;
    private boolean isConverted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }

    public boolean isConverted() {
        return isConverted;
    }

    public void setConverted(boolean converted) {
        isConverted = converted;
    }
}
