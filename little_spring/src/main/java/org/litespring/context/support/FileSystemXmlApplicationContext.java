package org.litespring.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.IOException;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String config) throws IOException {
        super(config);
    }

    @Override
    public Resource getResourceByPath(String config) {
        return new FileSystemResource(config);
    }
}
