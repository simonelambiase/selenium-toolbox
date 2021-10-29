package driver;

import entities.Configuration;
import org.openqa.selenium.WebDriver;


/**
 *
 * @Author: Simone Lambiase
 *
 * This is the driver handler, it's a container of the webdriver and for some utility entities.
 *
 */

class DriverHandler {

    private WebDriver driver;
    private Configuration configuration;
    private DriverType driverType;

    protected DriverHandler(WebDriver driver, DriverType type ) {
        this.driver = driver;
        this.driverType = type;
    }

    protected DriverHandler(WebDriver driver, String configurationFile, DriverType type  ) {
        this.driver = driver;
        this.configuration = new Configuration(configurationFile);
        this.driverType = type;
    }

    protected DriverHandler(WebDriver driver, Configuration configuration, DriverType type  ) {
        this.driver = driver;
        this.configuration = configuration;
        this.driverType = type;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setConfigurationFileName ( String configurationFileName ) {
        this.configuration = new Configuration(configurationFileName);
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public void openUrl ( String url ) {
        this.driver.get(url);
    }

    public void closeDriver() {
        driver.quit();
    }
}
