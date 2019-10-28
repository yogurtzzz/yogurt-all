package com.yogurt.handlerAdapter;

import com.yogurt.beanFactory.YogurtBeanFactory;
import com.yogurt.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestHandlerAdapter implements HandlerAdapter {
    @Override
    public Object handleRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvocationTargetException, IllegalAccessException {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Object beanInstance = YogurtBeanFactory.getBean(handlerMethod.getBeanName());
        Object returnVal = null;
        if (method != null && beanInstance != null){
            returnVal = method.invoke(beanInstance);
        }
        return returnVal;
    }

    @Override
    public boolean support(Object handler) {
        return handler instanceof HandlerMethod;
    }
}
