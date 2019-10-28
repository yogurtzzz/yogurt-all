package org.spring.beans.core.io;

import org.spring.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSystemResource implements Resource {
    //用JAVA原生的File API实现
    private String path;
    private File file;

    public FileSystemResource(String path){
        Assert.notNull(path,"path can not be null");
        this.path = path;
        this.file = new File(path);
    }
    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        if (file == null || !file.exists() || file.isDirectory())
            throw new FileNotFoundException(path + " does not exists, or is a directory");
        FileInputStream stream = new FileInputStream(file);
        return stream;
    }

    @Override
    public String getDescription() {
        return file.getAbsolutePath();
    }
}
