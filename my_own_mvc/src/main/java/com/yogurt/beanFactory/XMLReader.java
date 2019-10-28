package com.yogurt.beanFactory;

import com.yogurt.annotations.Controller;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class XMLReader {
    public static Map<String,YogurtBeanDefinition> getBeanDefinitionMap(String contextConfigLocation){
        try {
            Document document = getDocument(contextConfigLocation);
            Map<String,YogurtBeanDefinition> beanDefinitionMap = parseDocument(document);
            return beanDefinitionMap;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String,YogurtBeanDefinition> parseDocument(Document document){
        Map<String,YogurtBeanDefinition> beanDefinitionMap = new HashMap<>();
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for (Element element : elements){
            if ("bean".equals(element.getName())){
                parseBeanElement(element,beanDefinitionMap);
            }else{
                parseCustomElement(element,beanDefinitionMap);
            }
        }
        return beanDefinitionMap;
    }
    private static void parseCustomElement(Element element,Map<String,YogurtBeanDefinition> beanDefinitionMap){
        String name = element.getName();
        if ("component-scan".equals(name)){
            String packageName = element.attributeValue("package");
            List<String> beanClassNames = getBeanClassNamesByPackage(packageName);
            for (String beanClassName : beanClassNames){
                try {
                    Class<?> clazz = Class.forName(beanClassName);
                    if (!clazz.isAnnotationPresent(Controller.class)){
                        /** 包扫描方式，仅注入带@Controller注解的类 **/
                        continue;
                    }
                    String beanName = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
                    YogurtBeanDefinition definition = new YogurtBeanDefinition();
                    definition.setBeanName(beanName);
                    definition.setBeanClassName(beanClassName);
                    beanDefinitionMap.put(beanName, definition);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static List<String> getBeanClassNamesByPackage(String packageName){
        List<String> beanClassNames = new ArrayList<>();
        String packageDir = packageName.replaceAll("[.]","/");
        Enumeration<URL> dirs;
        boolean recursive = true;
        try{
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDir);
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)){
                    String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
                    findClassNamesByFile(packageName,filePath,recursive,beanClassNames);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanClassNames;
    }
    private static void findClassNamesByFile(String packageName,String filePath,boolean recursive,List<String> beanClassNames){
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()){
            return ;
        }
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                boolean acceptDir = recursive && pathname.isDirectory();
                boolean acceptClass = pathname.getName().endsWith(".class") || pathname.getName().endsWith(".java");
                return acceptDir || acceptClass;
            }
        });
        for (File file : files){
            if (file.isDirectory()){
                findClassNamesByFile(packageName+"."+file.getName(),file.getAbsolutePath(),recursive,beanClassNames);
            }else{
                String className = file.getName().substring(0,file.getName().lastIndexOf("."));
                beanClassNames.add(packageName+"."+className);
            }
        }
    }
    private static void parseBeanElement(Element element,Map<String,YogurtBeanDefinition> beanDefinitionMap){
        String id = element.attributeValue("id");
        String beanName = element.attributeValue("name");
        String beanClassName = element.attributeValue("class");
        YogurtBeanDefinition definition = new YogurtBeanDefinition();
        if (StringUtils.isBlank(beanClassName)){
            return ;
        }
        if (StringUtils.isBlank(beanName)){
            beanName = beanClassName.substring(beanClassName.lastIndexOf(".")+1);
        }
        if (StringUtils.isBlank(id)){
            id = String.valueOf(Objects.hash(beanName) + 1);
        }
        String initMethod = element.attributeValue("init-method");
        definition.setBeanName(beanName);
        definition.setBeanClassName(beanClassName);
        definition.setId(id);
        if (StringUtils.isNotBlank(initMethod)){
            definition.setInitMethod(initMethod);
        }
        parsePropertyElement(element,definition);
        beanDefinitionMap.put(beanName,definition);
    }

    private static void parsePropertyElement(Element element, YogurtBeanDefinition definition){
        List<Element> propertyElements = element.elements("property");
        for (Element propertyItem : propertyElements){
            String propertyName = propertyItem.attributeValue("name");
            String propertyValue = propertyItem.attributeValue("value");
            String propertyRef = propertyItem.attributeValue("ref");
            if (StringUtils.isBlank(propertyValue) && StringUtils.isBlank(propertyRef)){
                continue ;
            }
            if (StringUtils.isBlank(propertyName)){
                throw new RuntimeException("property name cannot be null");
            }
            definition.getPropertyValues().put(propertyName,propertyValue == null ? new RuntimeReferenceBean(propertyRef):propertyValue);
        }
    }

    private static Document getDocument(String contextConfigLocation) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(XMLReader.class.getClassLoader().getResourceAsStream(contextConfigLocation));
        return document;
    }
}
