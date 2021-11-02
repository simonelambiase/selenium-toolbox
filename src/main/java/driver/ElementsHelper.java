package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface ElementsHelper {

    WebElement getElement(By by);
    WebElement getElementVisibilityWithTimeout(By by, int timeout);
    WebElement getElementPresenceWithTimeout(By by, int timeout);
    void clickElement(By by);
    void clickElement(WebElement element);
    void clickElementJS ( By by );
    void clickElementJS (WebElement elem );
    void getAttribute ( By by, String attribute );
    void getAttribute ( WebElement element, String attribute );

}
