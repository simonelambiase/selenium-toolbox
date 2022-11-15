package driver;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverHandlerTest {


    static DriverHandler chromeDriver;
    static DriverHandler firefoxDriver;
    static DriverHandler edgeDriver;

    public void initDriver() {
    }


    @Test
    public void openChromedriver() {
        chromeDriver = DriverFactory.createDriver("CHROME");
        assertNotNull(chromeDriver);
        assertEquals(chromeDriver.getDriverType(),DriverType.CHROME);
        chromeDriver.openUrl("https://www.google.com");
        chromeDriver.closeDriver();

    }

    @Test
    public void openFirefoxDriver() {
        firefoxDriver = DriverFactory.createDriver("FIREFOX");
        assertNotNull(firefoxDriver);
        assertEquals(firefoxDriver.getDriverType(),DriverType.FIREFOX);
        firefoxDriver.openUrl("https://www.google.com");
        firefoxDriver.closeDriver();
    }

    @Test
    public void openEdgeDriver() {
        edgeDriver = DriverFactory.createDriver("EDGE");
        assertNotNull(edgeDriver);
        assertEquals(edgeDriver.getDriverType(),DriverType.EDGE);
        edgeDriver.openUrl("https://www.google.com");
        edgeDriver.closeDriver();
    }

    @AfterAll
    public void closeAllDrivers() {
        chromeDriver.closeDriver();
        firefoxDriver.closeDriver();
        edgeDriver.closeDriver();
    }


}