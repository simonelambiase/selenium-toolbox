package driver;

import entities.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @Author: Simone Lambiase
 *
 * This class is the Driver Factory, the user must use this class for create an istance of the DriverHandler.
 * The user can make a instance without a configuration file but he need also to configure himself the path of the drivers.
 *
 */

public class DriverFactory {

    static Logger log = LoggerUtils.createLogger(DriverFactory.class);

    public static DriverHandler createDriver ( String driverType ) {

        log.info("Setting drivers to default packaged drivers, maybe the version can't be correct...");

        if ( StringUtils.containsIgnoreCase(driverType,"CHROME") ) {
            log.info("Creating a Chrome istance...");
            WebDriverManager.chromedriver().setup();
            return new DriverHandler(WebDriverManager.chromedriver().create(),DriverType.CHROME);
        } else if ( StringUtils.containsIgnoreCase(driverType,"FIREFOX") ) {
            WebDriverManager.firefoxdriver().setup();
            log.info("Creating a Firefox istance...");
            return new DriverHandler(WebDriverManager.firefoxdriver().create(), DriverType.FIREFOX);
        } else if ( StringUtils.containsIgnoreCase(driverType,"EDGE") ) {
            log.info("Creating a Edge istance...");
            WebDriverManager.edgedriver().setup();
            return new DriverHandler(WebDriverManager.edgedriver().create(), DriverType.EDGE);
        } else {
            log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
            System.exit(1);
        }
        return null;
    }

    public static DriverHandler createDriver ( String driverType, String configurationFile ) {

        Configuration cfg = new Configuration(configurationFile);
        System.setProperty("webdriver.chrome.driver",cfg.getProperty("chromedriver.path"));
        System.setProperty("webdriver.gecko.driver",cfg.getProperty("geckodriver.path"));
        System.setProperty("webdriver.edge.driver",cfg.getProperty("edgedriver.path"));

        if ( StringUtils.containsIgnoreCase(driverType,"CHROME") ) {
            log.info("Creating a Chrome istance...");
            return new DriverHandler(new ChromeDriver(),cfg,DriverType.CHROME);
        } else if ( StringUtils.containsIgnoreCase(driverType,"FIREFOX") ) {
            log.info("Creating a Firefox istance...");
            return new DriverHandler(new FirefoxDriver(),cfg,DriverType.FIREFOX);
        } else if ( StringUtils.containsIgnoreCase(driverType,"EDGE") ) {
            log.info("Creating a Edge istance...");
            return new DriverHandler(new EdgeDriver(),cfg,DriverType.EDGE);
        } else {
            log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
            System.exit(1);
        }
        return null;
    }

    public static DriverHandler createDriver ( DriverType driverType ) {
        log.info("Setting drivers to default packaged drivers, maybe the version can't be correct...");

        switch ( driverType ) {
            case CHROME:
                log.info("Creating a Chrome istance...");
                WebDriverManager.chromedriver().setup();
                return new DriverHandler(WebDriverManager.chromedriver().create(),DriverType.CHROME);
            case FIREFOX:
                log.info("Creating a Firefox istance...");
                WebDriverManager.firefoxdriver().setup();
                return new DriverHandler(WebDriverManager.firefoxdriver().create(), DriverType.FIREFOX);
            case EDGE:
                log.info("Creating a Edge istance...");
                WebDriverManager.edgedriver().setup();
                return new DriverHandler(WebDriverManager.edgedriver().create(), DriverType.EDGE);
            default:
                log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
                System.exit(1);
        }
        return null;
    }

    public static DriverHandler createDriver ( DriverType driverType, String configurationFile ) {

        Configuration cfg = new Configuration(configurationFile);
        System.setProperty("webdriver.chrome.driver",cfg.getProperty("chromedriver.path"));
        System.setProperty("webdriver.firefox.driver",cfg.getProperty("geckodriver.path"));
        System.setProperty("webdriver.edge.driver",cfg.getProperty("edge.path"));

        switch ( driverType ) {
            case CHROME:
                log.info("Creating a Chrome istance...");
                return new DriverHandler(new ChromeDriver(),cfg,DriverType.CHROME);
            case EDGE:
                log.info("Creating a Firefox istance...");
                return new DriverHandler(new FirefoxDriver(),cfg,DriverType.FIREFOX);
            case FIREFOX:
                log.info("Creating a Edge istance...");
                return new DriverHandler(new EdgeDriver(),cfg,DriverType.EDGE);
            default:
                log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
                System.exit(1);
        }
        return null;
    }
}
