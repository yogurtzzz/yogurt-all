package org.spring.beans.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.bean.BeanElement;
import org.dom4j.io.SAXReader;
import org.spring.beans.core.di.PropertyValue;
import org.spring.beans.core.di.RuntimeReference;
import org.spring.beans.core.di.TypeStringValue;
import org.spring.beans.core.io.Resource;
import org.spring.beans.enums.BeanScope;
import org.spring.beans.exception.BeanDefinitionException;
import org.spring.beans.core.BeanDefinitionRegistry;
import org.spring.beans.support.GenericBeanDefinition;
import org.spring.utils.StringUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLBeanDefinitionParser {
    //解析XML，封装BeanDefinition，将BeanDefinition注册到BeanDefinitionRegistry
    private BeanDefinitionRegistry beanDefinitionRegistry;
    private static final String ROOT_ELEMENT = "beans";
    private static final String BEAN_ELEMENT = "bean";

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_SCOPE = "scope";

    private static final String PROPERTY_ELEMENT = "property";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_REF = "ref";

    public XMLBeanDefinitionParser(BeanDefinitionRegistry beanDefinitionRegistry){
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }
    public void loadBeanDefinition(Resource resource){
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (FileNotFoundException e) {
            throw new BeanDefinitionException("Resource not found : "+resource.getDescription(),e);
        }
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            //创建Document失败，抛出BeanDefinitionException，表示解析XML文件封装BeanDefinition时出错
            throw new BeanDefinitionException("Parsing XML Exception ",e);
        }
        Element rootElement = document.getRootElement();
        if (!ROOT_ELEMENT.equals(rootElement.getName()))
            throw new BeanDefinitionException("XML root element should be <beans>");
        List<Element> elements = rootElement.elements(BEAN_ELEMENT);
        for (Element item : elements){
            String elementName = item.getName();
            if (!BEAN_ELEMENT.equals(elementName))
                continue;
            String id = item.attributeValue(ATTRIBUTE_ID);
            String beanClassName = item.attributeValue(ATTRIBUTE_CLASS);
            String beanScopeStr = item.attributeValue(ATTRIBUTE_SCOPE);
            BeanScope scope;
            if (StringUtils.isBlank(beanClassName))
                continue;
            //scope默认是单例  singleTon
            if (beanScopeStr == null || "".equals(beanScopeStr))
                scope = BeanScope.SINGLETON;
            else
                scope = BeanScope.valueOf(beanScopeStr);

            //TODO
            List<Element> properties = item.elements(PROPERTY_ELEMENT);
            if (properties != null && !properties.isEmpty()){
                for (Element property : properties){
                    String name = property.attributeValue(ATTRIBUTE_NAME);
                    String value = property.attributeValue(ATTRIBUTE_VALUE);
                    String ref = property.attributeValue(ATTRIBUTE_REF);
                    PropertyValue pv = new PropertyValue();
                    pv.setName(name);
                    if (value != null && ref == null){
                        pv.setValue(new TypeStringValue(value));
                        pv.setConverted(false);
                    }else if (value == null && ref != null){
                        pv.setValue(new RuntimeReference(value));
                        pv.setConverted(true);
                    }else{
                        throw new BeanDefinitionException("either [value] or [ref] can be assigned,but not both");
                    }
                }
            }

            GenericBeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
            beanDefinition.setScope(scope);
            beanDefinitionRegistry.registerBeanDefinition(beanDefinition);
        }
    }
}
