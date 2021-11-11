package entities;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigurationTest {

    static Configuration cfg;

    @Before
    public void init() {
        cfg = new Configuration("src/main/java/resources/config/config_default.properties");
    }

    @Test
    public void notNull() {
        Assert.assertNotNull(cfg);
    }

    @Test
    public void getChromePath() {
        Assert.assertNotNull(cfg.getProperty("chromedriver.path"));
        Assert.assertEquals("The chrome path is not the same.", "src/main/java/resources/webdrivers/chromedriver.exe",cfg.getProperty("chromedriver.path"));
    }

    @Test
    public void notEmpty() {
        Assert.assertFalse(cfg.isEmpty());
    }
}