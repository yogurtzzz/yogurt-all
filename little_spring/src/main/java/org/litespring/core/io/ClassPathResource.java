package org.litespring.core.io;

import org.litespring.util.Assert;
import org.litespring.util.ClassLoaderUtils;

import java.io.InputStream;

/**
 * 用ClassLoader 从classpath下加载文件
 */
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path,null);
    }

    public ClassPathResource(String path,ClassLoader classLoader){
        Assert.assertNotNull(path,"path of xml can not be null");
        this.path = path;
        if (classLoader == null){
            this.classLoader = ClassLoaderUtils.getDefaultClassLoader();
        }else {
            this.classLoader = classLoader;
        }
    }
    @Override
    public InputStream getInputStream() {
        return this.classLoader.getResourceAsStream(path);
    }
}
