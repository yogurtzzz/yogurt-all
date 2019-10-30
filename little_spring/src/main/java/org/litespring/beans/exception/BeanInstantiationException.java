package org.litespring.beans.exception;

/**
 * 实例化bean对象时的异常
 */
public class BeanInstantiationException extends BeanException {
    public BeanInstantiationException(String message) {
        super(message);
    }

    public BeanInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanInstantiationException(Throwable cause) {
        super(cause);
    }
}
