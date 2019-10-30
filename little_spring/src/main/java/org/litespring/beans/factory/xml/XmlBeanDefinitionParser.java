package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.GenericBeanDefinition;
import org.litespring.beans.exception.BeanDefinitionParseException;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

//解析XML文件，装载BeanDefinition信息，并注册到BeanDefinitionRegistry
public class XmlBeanDefinitionParser {
    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String SCOPE_ATTRIBUTE = "scope";

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
            Iterator<Element> iterator = root.elementIterator();
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
                beanDefinitionRegistry.registerBeanDefinition(id,new GenericBeanDefinition(id,className,scope));
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
}
