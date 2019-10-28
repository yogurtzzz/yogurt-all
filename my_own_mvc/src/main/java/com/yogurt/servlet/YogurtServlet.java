package com.yogurt.servlet;

import com.yogurt.banners.MyBanners;
import com.yogurt.beanFactory.YogurtBeanFactory;
import com.yogurt.handlerAdapter.HandlerAdapter;
import com.yogurt.handlerMapping.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YogurtServlet extends BaseServlet {
    private List<HandlerMapping> handlerMappingList = new ArrayList<>();
    private List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    @Override
    public void init(ServletConfig config){
        /** 初始化Servlet **/
        System.out.println(MyBanners.LOVE);
        initBeanFactory(config);
        initHandlerMappings();
        initHandlerAdapter();
    }

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        Object handler = getHandler(req);
        if (handler == null){
            return ;
        }
        HandlerAdapter adapter = getAdapter(handler);
        if (adapter == null){
            return ;
        }
        try {
            Object returnVal = adapter.handleRequest(req,resp,handler);
            resp.getWriter().write(returnVal.toString());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object getHandler(HttpServletRequest req){
        for (HandlerMapping handlerMapping : handlerMappingList){
            Object handler = handlerMapping.getHandler(req);
            if (handler != null){
                return handler;
            }
        }
        return null;
    }
    private HandlerAdapter getAdapter(Object handler){
        for (HandlerAdapter adapter : handlerAdapterList){
            if (adapter.support(handler)){
                return adapter;
            }
        }
        return null;
    }

    public void initBeanFactory(ServletConfig config){
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        YogurtBeanFactory.initBeanFactory(contextConfigLocation);
    }
    public void initHandlerMappings(){
        List<HandlerMapping> handlerMappings = YogurtBeanFactory.getBeansOfType(HandlerMapping.class);
        handlerMappingList.addAll(handlerMappings);
    }
    public void initHandlerAdapter(){
        List<HandlerAdapter> handlerAdapters = YogurtBeanFactory.getBeansOfType(HandlerAdapter.class);
        handlerAdapterList.addAll(handlerAdapters);
    }
}
