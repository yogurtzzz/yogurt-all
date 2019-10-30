package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import java.io.IOException;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


    public ClassPathXmlApplicationContext(String config) throws IOException {
        super(config);
    }

    @Override
    public Resource getResourceByPath(String config) {
        return new ClassPathResource(config,this.getBeanClassLoader());
    }
}
