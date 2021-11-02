package driver;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ElementsHelperDefaultTest {


    @Test
    public void getElement() {
        DriverHandler driver = DriverFactory.createDriver("CHROME","src/main/resources/config/config_default.properties");
        assertNotNull(driver);
        assertNotNull(driver.getDriver());
        File dummyPage = new File("src/main/resources/dummy_pages/dummy1.html");
        assertNotNull(dummyPage);
        assertTrue(dummyPage.exists());
        driver.openUrl(dummyPage.getAbsolutePath());
        WebElement e1 = driver.getElementsHelper().getElement(By.xpath("//p[@id='p1']"));
        assertNotNull(e1);
        driver.closeDriver();
    }

    @Test
    public void getElementVisibilityWithTimeout() {
        DriverHandler driver = DriverFactory.createDriver("CHROME","src/main/resources/config/config_default.properties");
        assertNotNull(driver);
        assertNotNull(driver.getDriver());
        File dummyPage = new File("src/main/resources/dummy_pages/dummy1.html");
        assertNotNull(dummyPage);
        assertTrue(dummyPage.exists());
        driver.openUrl(dummyPage.getAbsolutePath());
        WebElement e1 = driver.getElementsHelper().getElementVisibilityWithTimeout(By.xpath("//p[@id='p1']"),60);
        assertNotNull(e1);
        driver.closeDriver();
    }

    @Test
    public void getElementPresenceWithTimeout() {
        DriverHandler driver = DriverFactory.createDriver("CHROME","src/main/resources/config/config_default.properties");
        assertNotNull(driver);
        assertNotNull(driver.getDriver());
        File dummyPage = new File("src/main/resources/dummy_pages/dummy1.html");
        assertNotNull(dummyPage);
        assertTrue(dummyPage.exists());
        driver.openUrl(dummyPage.getAbsolutePath());
        WebElement e1 = driver.getElementsHelper().getElementPresenceWithTimeout(By.xpath("//p[@id='p1']"),60);
        assertNotNull(e1);
        driver.closeDriver();
    }

    @Test
    public void clickElement() {
        DriverHandler driver = DriverFactory.createDriver("CHROME","src/main/resources/config/config_default.properties");
        assertNotNull(driver);
        assertNotNull(driver.getDriver());
        File dummyPage = new File("src/main/resources/dummy_pages/dummy1.html");
        assertNotNull(dummyPage);
        assertTrue(dummyPage.exists());
        driver.openUrl(dummyPage.getAbsolutePath());
        driver.getElementsHelper().clickElement(By.xpath("//input[@id='i1']"));
        driver.closeDriver();
    }

    @Test
    public void testClickElement() {
    }

    @Test
    public void clickElementJS() {
        DriverHandler driver = DriverFactory.createDriver("CHROME","src/main/resources/config/config_default.properties");
        assertNotNull(driver);
        assertNotNull(driver.getDriver());
        File dummyPage = new File("src/main/resources/dummy_pages/dummy1.html");
        assertNotNull(dummyPage);
        assertTrue(dummyPage.exists());
        driver.openUrl(dummyPage.getAbsolutePath());
        driver.getElementsHelper().clickElementJS(By.xpath("//input[@id='i1']"));
        driver.closeDriver();
    }

    @Test
    public void getElementByText() {
        DriverHandler driver = DriverFactory.createDriver("CHROME","src/main/resources/config/config_default.properties");
        assertNotNull(driver);
        assertNotNull(driver.getDriver());
        File dummyPage = new File("src/main/resources/dummy_pages/dummy1.html");
        assertNotNull(dummyPage);
        assertTrue(dummyPage.exists());
        driver.openUrl(dummyPage.getAbsolutePath());
        assertNotNull(driver.getElementByText("PARAGRAPH"));
        assertNotNull(driver.getElementByText("HELLO WORLD"));
    }



}