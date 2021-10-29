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

    protected DriverHandler(WebDriver driver) {
        this.driver = driver;
    }

    protected DriverHandler(WebDriver driver, String configurationFile ) {
        this.driver = driver;
        this.configuration = new Configuration(configurationFile);
    }

    protected DriverHandler(WebDriver driver, Configuration configuration ) {
        this.driver = driver;
        this.configuration = configuration;
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

    public void openUrl ( String url ) {
        this.driver.get(url);
    }
}
