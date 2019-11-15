package com.yogurt.beanFactory;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class YogurtBeanFactory {
    private static Map<String,Object> beanInstanceMapByName = new HashMap<>();
    private static Map<Class<?>,Object> beanInstanceMapByType = new HashMap<>();
    private static Map<String,YogurtBeanDefinition> beanDefinitionMap = new HashMap<>();

    /** 首先，初始化时先加载xml文件，封装成beanDefinition，后根据beanDefinition来实例化bean */
    public static void initBeanFactory(String contextConfigLocation){
        beanDefinitionMap = XMLReader.getBeanDefinitionMap(contextConfigLocation);
        instantiateBeans();
    }

    private static void instantiateBeans(){
        if (beanDefinitionMap != null && beanDefinitionMap.size() > 0){
            for (String beanName : beanDefinitionMap.keySet()){
                getBean(beanName);
            }
        }
    }
    public static Object getBean(String beanName){
        Object beanInstance;
        try {
            beanInstance = beanInstanceMapByName.get(beanName);
            if (beanInstance == null) {
                /** bean实例不存在，则创建 */
                YogurtBeanDefinition definition = beanDefinitionMap.get(beanName);
                String beanClassName = definition.getBeanClassName();
                Class<?> clazz = Class.forName(beanClassName);
                beanInstance = clazz.newInstance();
                /** 下面实现依赖注入 **/
                Map<String,Object> propertyValueMap = definition.getPropertyValues();
                for (String propertyName : propertyValueMap.keySet()){
                    Object value = propertyValueMap.get(propertyName);
                    Object propertyValue;
                    if (value instanceof RuntimeReferenceBean){
                        /** ref **/
                        String refBeanName = ((RuntimeReferenceBean) value).getBeanName();
                        propertyValue = getBean(refBeanName);
                    }else{
                        /** 普通属性 */
                        propertyValue = value;
                    }
                    Field field = clazz.getDeclaredField(propertyName);
                    field.setAccessible(true);
                    field.set(beanInstance,propertyValue);
                }
                /** 下面调用init方法 **/
                String initName = definition.getInitMethod();
                if (StringUtils.isNotBlank(initName)){
                    Method method = clazz.getDeclaredMethod(initName);
                    method.invoke(beanInstance);
                }
                beanInstanceMapByName.put(beanName,beanInstance);
                beanInstanceMapByType.put(clazz,beanInstance);
            }
            return beanInstance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getBean(Class<?> clazz){
        return beanInstanceMapByType.get(clazz);
    }

    public static List<String> getBeanNamesOfType(Class<?> type){
        /** 获取所有type以及其子类的bean名称 */
        try {
            List<String> beanNames = new ArrayList<>();
            for (String beanName : beanDefinitionMap.keySet()) {
                YogurtBeanDefinition definition = beanDefinitionMap.get(beanName);
                Class<?> clazz = Class.forName(definition.getBeanClassName());
                if (type.isAssignableFrom(clazz)){
                    /** 若type是clazz的父类 **/
                    beanNames.add(beanName);
                }
            }
            return beanNames;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Class<?> getType(String beanName){
        try {
            YogurtBeanDefinition definition = beanDefinitionMap.get(beanName);
            if (definition != null) {
                return Class.forName(definition.getBeanClassName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static<T> List<T> getBeansOfType(Class<?> type){
        List<T> result = new ArrayList<>();
        for (Class<?> clazz : beanInstanceMapByType.keySet()){
            if (type.isAssignableFrom(clazz)){
                Object obj = beanInstanceMapByType.get(clazz);
                result.add((T)obj);
            }
        }
        return result;
    }
}
