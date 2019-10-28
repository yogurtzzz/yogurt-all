package org.spring.beans.core.io;

import jdk.internal.util.xml.impl.Input;
import org.spring.utils.ClassLoaderUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    //ClassPath下的资源文件，可用ClassLoader来加载
    private String path;
    private ClassLoader classLoader;
    public ClassPathResource(String path){
        this(path,null);
    }

    public ClassPathResource(String path,ClassLoader classLoader){
        this.path = path;
        this.classLoader = (classLoader == null) ? ClassLoaderUtils.getDefaultClassLoader() : classLoader;
    }
    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream stream = classLoader.getResourceAsStream(this.path);
        if (stream == null)
            throw new FileNotFoundException(path + "can not be found");
        return stream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
