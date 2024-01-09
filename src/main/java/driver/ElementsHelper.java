package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public interface ElementsHelper {

    WebElement getElement(By by);
    WebElement getElementVisibilityWithTimeout(By by, int timeout);
    WebElement getElementPresenceWithTimeout(By by, int timeout);
    WebElement getElementByText ( String text );
    List<WebElement> getElementsByText (String text );
    List<WebElement> getElementsByTag ( String tagName );
    List<WebElement> getElementsBy ( By by );
    Optional<String> getAttribute (WebElement elem, String attributeName );
    Optional<String> getAttribute ( By by, String attributeName );
    void clickElement(By by);
    void clickElement(WebElement element);
    void clickElementJS ( By by );
    void clickElementJS (WebElement elem );

    void fillElementByText ( By by, String text );
    void fillElementByText ( WebElement elem, String text );

}
