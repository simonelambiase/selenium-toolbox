package driver;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverHandlerTest {


    static DriverHandler chromeDriver;
    static DriverHandler firefoxDriver;
    static DriverHandler edgeDriver;

    @Before
    public void initDriver() {
        chromeDriver = DriverHelper.createDriver("CHROME", "src/main/resources/config/config_default.properties");
        firefoxDriver = DriverHelper.createDriver("FIREFOX", "src/main/resources/config/config_default.properties");
        edgeDriver = DriverHelper.createDriver("EDGE", "src/main/resources/config/config_default.properties");
    }

    @Test
    public void openChromedriver() {
        assertNotNull(chromeDriver);
        assertEquals(chromeDriver.getDriverType(),DriverType.CHROME);
        chromeDriver.openUrl("https://www.google.com");
        chromeDriver.closeDriver();

    }

    @Test
    public void openFirefoxDriver() {
        assertNotNull(firefoxDriver);
        assertEquals(firefoxDriver.getDriverType(),DriverType.FIREFOX);
        firefoxDriver.openUrl("https://www.google.com");
        firefoxDriver.closeDriver();
    }

    @Test
    public void openEdgeDriver() {
        assertNotNull(edgeDriver);
        assertEquals(edgeDriver.getDriverType(),DriverType.EDGE);
        edgeDriver.openUrl("https://www.google.com");
        edgeDriver.closeDriver();
    }
}