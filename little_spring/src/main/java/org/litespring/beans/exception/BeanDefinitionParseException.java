package org.litespring.beans.exception;

/**
 * dom4j 解析 xml，装载beanDefinition时的异常
 */
public class BeanDefinitionParseException extends BeanException {
    public BeanDefinitionParseException(String message) {
        super(message);
    }

    public BeanDefinitionParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanDefinitionParseException(Throwable cause) {
        super(cause);
    }
}
