package entities;

import org.slf4j.Logger;
import utils.LoggerUtils;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private Logger log = LoggerUtils.createLogger(this.getClass());
    private File configFile;
    private Properties properties;
    private String fileName;
    private boolean empty = false;

    public Configuration(String fileName) {
        try {
            this.fileName = fileName;
            this.configFile = new File(fileName);
            this.properties = new Properties();
            this.properties.load(new FileReader(fileName));
        } catch ( IOException e ) {
            empty = true;
            log.error("The property file is empty.");
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getProperty ( String propertyName ) {
        return properties.getProperty(propertyName);
    }
}
