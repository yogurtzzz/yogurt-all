package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

//这个包下全是接口  API
//实现类放在support包下
public interface BeanFactory {
    Object getBean(String beanId);
}
