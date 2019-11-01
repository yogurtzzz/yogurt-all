package org.litespring.beans.factory.support;


import org.litespring.beans.factory.BeanFactory;
import org.litespring.core.di.*;


public class BeanPropertyValueResolver {
    private BeanFactory beanFactory;
    public BeanPropertyValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object obj,Class<?> clzz) {
        if (!(obj instanceof RuntimeBeanReference || obj instanceof TypedStringValue))
            throw new IllegalStateException("property value only support string and reference");

        if (obj instanceof RuntimeBeanReference){
            return beanFactory.getBean(((RuntimeBeanReference) obj).getBeanName());
        }else{
            //TypedStringValue
            String stringValue = ((TypedStringValue) obj).getValue();
            if (String.class == clzz){
                return stringValue;
            }else if (Number.class.isAssignableFrom(clzz)){
                TypeConverter converter = new NumberConverter();
                return converter.convert(stringValue,clzz);
            }else if (Boolean.class.isAssignableFrom(clzz)){
                TypeConverter converter = new BooleanConverter();
                return converter.convert(stringValue,clzz);
            }else {
                throw new RuntimeException("not support the conversion of this type");
            }
        }
    }
}
