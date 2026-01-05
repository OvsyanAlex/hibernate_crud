package org.example.util;

import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
            System.out.println("properties are update");
        } catch (Exception e) {
            throw new RuntimeException("Reading properties error", e);
        }
    }

    public static String getProperties(String propertiesKey) {
        return properties.getProperty(propertiesKey);
    }

    public static Properties getAllProperties() {
        return properties;
    }
}
