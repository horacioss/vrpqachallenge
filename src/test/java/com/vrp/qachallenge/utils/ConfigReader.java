package com.vrp.qachallenge.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
