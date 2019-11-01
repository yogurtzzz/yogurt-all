package org.litespring.core.di;

import org.litespring.util.StringUtils;

public class BooleanConverter implements TypeConverter {
    @Override
    public Object convert(String value, Class<?> requiredType) {
        if (!Boolean.class.isAssignableFrom(requiredType))
            throw new IllegalArgumentException("Boolean Converter only support conversion to boolean");
        return isTrue(value);
    }
    private boolean isTrue(String value){
        if (StringUtils.isBlank(value))
            return false;
        if ("true".equals(value) || "True".equals(value))
            return true;
        //正数也认为是true
        if (value.matches("[1-9]+"))
            return true;
        return false;
    }
}
