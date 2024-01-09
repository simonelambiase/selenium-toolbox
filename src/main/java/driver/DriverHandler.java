package driver;

import entities.Configuration;
import entities.WebDriverConfiguration;
import org.apache.zookeeper.Op;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import utils.LoggerUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 *
 * @Author: Simone Lambiase
 *
 * This is the driver handler, it's a container of the webdriver and for some utility entities.
 *
 */

public class DriverHandler {

    private WebDriver driver;
    private WebDriverConfiguration configuration;
    private ElementsHelper elementsHelper;
    private DriverType driverType;
    private Logger log = LoggerUtils.createLogger(this.getClass());

    protected DriverHandler(WebDriver driver, DriverType type ) {
        this.driver = driver;
        this.driverType = type;
        this.elementsHelper = new ElementsHelperDefault(driver);
    }

    protected DriverHandler(WebDriver driver, WebDriverConfiguration configuration, DriverType type  ) {
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

    public WebDriverConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(WebDriverConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setElementsHelper(ElementsHelper elementsHelper) {
        this.elementsHelper = elementsHelper;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
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

    public List<WebElement> getElementsBy ( By by ) {
        return elementsHelper.getElementsBy(by);
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

    public void fillElementByText(By by, String text) {
        elementsHelper.fillElementByText(by,text);
    }

    public void fillElementByText(WebElement elem, String text) {
        elementsHelper.fillElementByText(elem,text);
    }

    public void enableNetworkTracking() {
        DevTools devTools;
        switch ( this.driverType ) {
            case CHROME:
                ChromeDriver chromeDriver = (ChromeDriver) this.driver;
                devTools = chromeDriver.getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
                devTools.addListener(Network.responseReceived(), System.out::println);
                break;
            case EDGE:
                EdgeDriver edgeDriver = (EdgeDriver) this.driver;
                devTools = edgeDriver.getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
                devTools.addListener(Network.responseReceived(), System.out::println);
                break;
            case FIREFOX:
                FirefoxDriver firefoxDriver = (FirefoxDriver) this.driver;
                devTools = firefoxDriver.getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
                devTools.addListener(Network.responseReceived(), responseReceived -> {
                    System.out.println(responseReceived.getResponse());
                });
                break;
        }
    }


}
