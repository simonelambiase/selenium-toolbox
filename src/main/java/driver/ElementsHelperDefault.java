package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import utils.LoggerUtils;

import java.util.List;
import java.util.Optional;

public class ElementsHelperDefault implements ElementsHelper {

    private Logger log = LoggerUtils.createLogger(ElementsHelperDefault.class);
    private WebDriver driver;
    private WebDriverWait wait;
    int defaultTimeout = 60;

    public ElementsHelperDefault( WebDriver driver ) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,defaultTimeout);
    }

    @Override
    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public WebElement getElementVisibilityWithTimeout(By by, int timeout) {
        return new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Override
    public WebElement getElementPresenceWithTimeout(By by, int timeout) {
        return new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Override
    public WebElement getElementByText(String text) {
        return driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
    }

    @Override
    public List<WebElement> getElementsByText(String text) {
        return driver.findElements(By.xpath("//*[contains(text(),'"+text+"')]"));
    }

    @Override
    public List<WebElement> getElementsByTag(String tagName) {
        return driver.findElements(By.tagName(tagName));
    }

    @Override
    public Optional<String> getAttribute(WebElement elem, String attributeName) {
        try {
            return Optional.of(elem.getAttribute(attributeName));
        } catch ( NullPointerException e ) {
            log.error("The attribute " + attributeName + "is not present");
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getAttribute(By by, String attributeName) {
        try {
            return Optional.of(getElement(by).getAttribute(attributeName));
        } catch ( NullPointerException e ) {
            log.error("The attribute " + attributeName + "is not present");
        }
        return Optional.empty();
    }


    @Override
    public void clickElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    @Override
    public void clickElement(WebElement element) {
        element.click();
    }

    @Override
    public void clickElementJS ( By by ) {
        JavascriptExecutor exc = (JavascriptExecutor) driver;
        exc.executeScript("arguments[0].click",getElement(by));
    }

    @Override
    public void clickElementJS (WebElement elem ) {
        JavascriptExecutor exc = (JavascriptExecutor) driver;
        exc.executeScript("arguments[0].click",elem);
    }
}
