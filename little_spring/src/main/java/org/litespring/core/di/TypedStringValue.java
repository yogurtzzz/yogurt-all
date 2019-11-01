package org.litespring.core.di;

/**
 * 用于value的注入
 */
public class TypedStringValue {
    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
