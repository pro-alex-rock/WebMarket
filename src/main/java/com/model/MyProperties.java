package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {
    Properties properties = new Properties();

    private String resource = "/application.properties";

    public String getProperty(String prop) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(resource);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Couldnt load properties.", e);
        }
        return properties.getProperty(prop);
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
