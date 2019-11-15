package com.yogurt.handlerMapping;

import com.yogurt.annotations.Controller;
import com.yogurt.annotations.RequestMapping;
import com.yogurt.beanFactory.YogurtBeanFactory;
import com.yogurt.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
    private static Map<String,HandlerMethod> handlerMapping = new HashMap<>();
    public void init(){
        List<String> beanNames = YogurtBeanFactory.getBeanNamesOfType(Object.class);
        for (String beanName : beanNames){
            Class<?> clazz = YogurtBeanFactory.getType(beanName);
            if (isHandler(clazz)){
                /** 是一个处理器 **/
                /** 遍历其方法 */
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods){
                    /** 看哪些方法有RequestMapping注解 **/
                    if (method.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        String url = requestMapping.value();
                        HandlerMethod handlerMethod = new HandlerMethod();
                        handlerMethod.setBeanName(beanName);
                        handlerMethod.setBeanType(clazz);
                        handlerMethod.setMethod(method);
                        handlerMapping.put(url,handlerMethod);
                    }
                }
            }
        }
    }
    @Override
    public Object getHandler(HttpServletRequest req) {
        return handlerMapping.get(req.getRequestURI());
    }
    private boolean isHandler(Class<?> clazz){
        return clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(RequestMapping.class);
    }
}
