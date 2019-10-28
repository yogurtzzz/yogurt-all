package org.spring.context.support;

import org.spring.beans.core.io.ClassPathResource;
import org.spring.beans.core.io.Resource;


public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String configPath) {
        super(configPath);
    }

    @Override
    public Resource getResourceByPath(String configPath) {
        return new ClassPathResource(configPath);
    }
}
