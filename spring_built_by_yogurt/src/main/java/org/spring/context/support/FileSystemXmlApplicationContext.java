package org.spring.context.support;

import org.spring.beans.core.io.FileSystemResource;
import org.spring.beans.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {
    public FileSystemXmlApplicationContext(String configPath) {
        super(configPath);
    }

    @Override
    public Resource getResourceByPath(String configPath) {
        return new FileSystemResource(configPath);
    }
}
