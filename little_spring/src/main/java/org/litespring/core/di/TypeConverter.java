package org.litespring.core.di;

public interface TypeConverter {
    Object convert(String value,Class<?> requiredType);
}
