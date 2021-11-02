package driver;

import entities.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import utils.LoggerUtils;

import java.util.List;
import java.util.NoSuchElementException;


/**
 *
 * @Author: Simone Lambiase
 *
 * This is the driver handler, it's a container of the webdriver and for some utility entities.
 *
 */

public class DriverHandler {

    private WebDriver driver;
    private Configuration configuration;
    private ElementsHelper elementsHelper;
    private DriverType driverType;
    private Logger log = LoggerUtils.createLogger(this.getClass());

    protected DriverHandler(WebDriver driver, DriverType type ) {
        this.driver = driver;
        this.driverType = type;
        this.elementsHelper = new ElementsHelperDefault(driver);
    }

    protected DriverHandler(WebDriver driver, String configurationFile, DriverType type  ) {
        this.driver = driver;
        this.configuration = new Configuration(configurationFile);
        this.driverType = type;
        this.elementsHelper = new ElementsHelperDefault(driver);
    }

    protected DriverHandler(WebDriver driver, Configuration configuration, DriverType type  ) {
        this.driver = driver;
        this.configuration = configuration;
        this.driverType = type;
        this.elementsHelper = new ElementsHelperDefault(driver);
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

    public ElementsHelper getElementsHelper() {
        return elementsHelper;
    }

    public void closeDriver() {
        driver.quit();
    }

    public void openUrl ( String url ) {
        driver.get(url);
    }

    public void clickElement ( By by ) {
        try {
            elementsHelper.clickElement(by);
        } catch ( NoSuchElementException e ) {
            log.error("The element with by " + by + " is not present.");
        }
    }

    public void clickElement ( WebElement element ) {
        elementsHelper.clickElement(element);
    }

    public WebElement getElement ( By by ) {
        return elementsHelper.getElement(by);
    }

    public WebElement getElementByText ( String text ) {
        return elementsHelper.getElementByText(text);
    }

    public List<WebElement> getElementsByText ( String text ) {
        return elementsHelper.getElementsByText(text);
    }

    public List<WebElement> getElementsByTag ( String tagName ) {
        return elementsHelper.getElementsByTag(tagName);
    }


}
