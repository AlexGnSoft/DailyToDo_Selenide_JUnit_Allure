package helpfiles;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {
    public static Properties properties;

    public PropertiesFile(){
        BufferedReader reader;
        String propertyFilePath = "src/main/java/helpfiles/config.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if(browser != null) return browser;
        else throw new RuntimeException("Browser is not specified in the config.properties file");
    }

    public String getBrowserSize() {
        String browser_size = properties.getProperty("browser_size");
        if(browser_size != null) return browser_size;
        else throw new RuntimeException("Browser_size is not specified in the config.properties file");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("baseUrl");
        if(url != null) return url;
        else throw new RuntimeException("Base url is not specified in the config.properties file");
    }
}
