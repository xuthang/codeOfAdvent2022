package org.example.FileReader;

import java.io.InputStream;

public class FileAccess {
    private String fileName;

    public FileAccess(String fileName) {
        this.fileName = fileName;
    }

    public StreamFileReader getFileReader()
    {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new StreamFileReader(inputStream);
        }
    }
}
