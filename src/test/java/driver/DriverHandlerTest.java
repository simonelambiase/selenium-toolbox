package driver;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverHandlerTest {


    static DriverHandler driver;

    @Before
    public void initDriver() {
        driver = DriverHelper.createDriver("CHROME", "src/main/resources/config/config_default.properties");
    }

    @Test
    public void openGoogle() {
        assertNotNull(driver);
        driver.openUrl("https://www.google.com");
    }
}