package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.GenericBeanDefinition;
import org.litespring.beans.exception.BeanDefinitionParseException;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.core.di.PropertyValue;
import org.litespring.core.di.RuntimeBeanReference;
import org.litespring.core.di.TypedStringValue;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassLoaderUtils;
import org.litespring.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

//解析XML文件，装载BeanDefinition信息，并注册到BeanDefinitionRegistry
public class XmlBeanDefinitionParser {
    private static final String BEAN_ELEMENT = "bean";
    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String SCOPE_ATTRIBUTE = "scope";

    private static final String PROPERTY_ELEMENT = "property";
    private static final String PROPERTY_NAME_ATTRIBUTE = "name";
    private static final String PROPERTY_REF_ATTRIBUTE = "ref";
    private static final String PROPERTY_VALUE_ATTRIBUTE = "value";


    private BeanDefinitionRegistry beanDefinitionRegistry;
    public XmlBeanDefinitionParser(BeanDefinitionRegistry beanDefinitionRegistry){
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinition(Resource resource) throws IOException {
        //使用dom4j对configFile进行解析
        //由原先的String入参，修改为Resource入参
        InputStream inputStream = resource.getInputStream();
        SAXReader xmlReader = new SAXReader();
        try {
            Document document = xmlReader.read(inputStream);
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator(BEAN_ELEMENT);
            while (iterator.hasNext()){
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String className = element.attributeValue(CLASS_ATTRIBUTE);
                String scope = element.attributeValue(SCOPE_ATTRIBUTE);
                if (className == null){
                    throw new BeanDefinitionParseException("beanClassName cannot be null");
                }
                if (id == null){
                    int lastIndex = className.lastIndexOf(".");
                    id = className.substring(lastIndex == -1 ? 0 : lastIndex);
                }
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,className,scope);
                parsePropertyElement(element,beanDefinition);
                beanDefinitionRegistry.registerBeanDefinition(id,beanDefinition);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionParseException(e);
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new BeanDefinitionParseException(e);
            }
        }
    }
    private void parsePropertyElement(Element element,BeanDefinition beanDefinition){
        Iterator<Element> iterator2 = element.elementIterator(PROPERTY_ELEMENT);
        while (iterator2.hasNext()){
            Element propertyElement = iterator2.next();
            String propertyName = propertyElement.attributeValue(PROPERTY_NAME_ATTRIBUTE);
            String propertyValue = propertyElement.attributeValue(PROPERTY_VALUE_ATTRIBUTE);
            String propertyRef = propertyElement.attributeValue(PROPERTY_REF_ATTRIBUTE);
            if (StringUtils.isBlank(propertyName)){
                throw new BeanDefinitionParseException("property name can not be absent");
            }
            if (propertyValue == null && StringUtils.isBlank(propertyRef)){
                throw new BeanDefinitionParseException("property value can not be absent");
            }
            if (propertyValue != null && StringUtils.isNotBlank(propertyRef)){
                throw new BeanDefinitionParseException("value and ref can not both be assigned");
            }
            if (propertyValue != null)
                beanDefinition.addPropertyValue(new PropertyValue(propertyName,new TypedStringValue(propertyValue)));
            else
                beanDefinition.addPropertyValue(new PropertyValue(propertyName,new RuntimeBeanReference(propertyRef)));
        }
    }
}
