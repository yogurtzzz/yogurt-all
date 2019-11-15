package com.yogurt.handlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface HandlerAdapter {
    Object handleRequest(HttpServletRequest request, HttpServletResponse response,Object handler) throws InvocationTargetException, IllegalAccessException;
    boolean support(Object handler);
}
