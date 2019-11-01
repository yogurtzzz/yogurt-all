package org.litespring.core.di;

public class NumberConverter implements TypeConverter {
    @Override
    public Object convert(String value, Class<?> requiredType) {
        if (!Number.class.isAssignableFrom(requiredType)){
            throw new IllegalArgumentException("Number Converter only support conversion to number");
        }
        if (requiredType == Integer.class){
            return Integer.parseInt(value);
        }else if (requiredType == Short.class){
            return Short.parseShort(value);
        }else if (requiredType == Long.class){
            return Long.parseLong(value);
        }else if (requiredType == Double.class){
            return Double.parseDouble(value);
        }else if (requiredType == Float.class){
            return Float.parseFloat(value);
        }else{
            throw new RuntimeException("Type not implemented yet");
        }
    }
}
