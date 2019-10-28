package org.spring.context;

import org.spring.beans.factory.BeanFactory;
import org.spring.beans.factory.ConfigurableBeanFactory;

public interface ApplicationContext extends ConfigurableBeanFactory {
    //ApplicationContext可能有多个实现类，如
        //ClassPathXmlApplicationContext
        //FileSystemXmlApplicationContext
    //它们都是读取XML文件，获取BeanDefinition，获取Bean实例
    //不同之处在于，获取XML的方式不同，一个是从ClassPath下获取，一个是从文件系统去获取
    //获取完XML后，都会形成一个InputStream，交给dom4j去解析
    //故可以把获取XML，形成InputStream这个共同的步骤，提取出来，抽象成一个接口 Resource

    //Resource <interface>   包含一个方法  getInputStream
    //有2个实现 =>
        //ClassPathResource
        //FileSystemResource

    //ClassPathXmlApplicationContext 和 FileSystemXmlApplicationContext ，只在获取Resource时行为不同，其余行为是相同的
    //故可抽象一个AbstractApplicationContext，使用 <模板方法> 的设计模式，来进行优化
}
