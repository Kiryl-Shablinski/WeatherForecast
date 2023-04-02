package com.example.weatherforecast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigApi {
    private static final String key;
    static {
        FileInputStream fin = null;
        Properties property = new Properties();
        try {
            fin = new FileInputStream("src/main/resources/config.properties");
            property.load(fin);
            key = property.getProperty("keyApi");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getKey() {
        return key;
    }
}
