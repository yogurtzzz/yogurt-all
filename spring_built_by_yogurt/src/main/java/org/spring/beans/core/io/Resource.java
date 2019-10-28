package org.spring.beans.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface Resource {
    InputStream getInputStream() throws FileNotFoundException;
    String getDescription();
}
