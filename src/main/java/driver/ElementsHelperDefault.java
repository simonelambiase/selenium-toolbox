package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsHelperDefault implements ElementsHelper {

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
        exc.executeScript("arguments[0].click",by);
    }

    @Override
    public void clickElementJS (WebElement elem ) {
        JavascriptExecutor exc = (JavascriptExecutor) driver;
        exc.executeScript("arguments[0].click",elem);
    }
}
