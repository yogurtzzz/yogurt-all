package org.spring.beans.exception;

//主流程就2步
//1.解析XML并获取BeanDefinition
//2.根据BeanDefinition创建bean实例

//故定义2个异常
public class BeanCreationException extends BeanException {
    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
