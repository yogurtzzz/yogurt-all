package config;

import niceFood.BeanAOP;
import org.springframework.context.annotation.*;
import pojo.Fruit;

@Configuration
@EnableAspectJAutoProxy
//@ComponentScan(basePackages = {"service","aop","pojo"})
@ComponentScan(basePackageClasses = {BeanAOP.class, Fruit.class})
public class ServiceConfig {
}
