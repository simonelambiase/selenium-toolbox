package driver;

import entities.Configuration;
import entities.WebDriverConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
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
            WebDriverManager.chromedriver()
                    .forceDownload()
                    .setup();
            return new DriverHandler(WebDriverManager.chromedriver().create(),DriverType.CHROME);
        } else if ( StringUtils.containsIgnoreCase(driverType,"FIREFOX") ) {
            WebDriverManager.firefoxdriver()
                    .forceDownload()
                    .setup();
            log.info("Creating a Firefox istance...");
            return new DriverHandler(WebDriverManager.firefoxdriver().create(), DriverType.FIREFOX);
        } else if ( StringUtils.containsIgnoreCase(driverType,"EDGE") ) {
            log.info("Creating a Edge istance...");
            WebDriverManager.edgedriver()
                    .forceDownload()
                    .setup();
            return new DriverHandler(WebDriverManager.edgedriver().create(), DriverType.EDGE);
        } else {
            log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
            System.exit(1);
        }
        return null;
    }

    public static DriverHandler createDriver ( DriverType driverType ) {

        switch ( driverType ) {
            case CHROME:
                log.info("Creating a Chrome istance...");
                return new DriverHandler(new ChromeDriver(),new WebDriverConfiguration(DriverType.CHROME),DriverType.CHROME);
            case FIREFOX:
                log.info("Creating a Firefox istance...");
                return new DriverHandler(new FirefoxDriver(),new WebDriverConfiguration(DriverType.FIREFOX),DriverType.FIREFOX);
            case EDGE:
                log.info("Creating a Edge istance...");
                return new DriverHandler(new EdgeDriver(),new WebDriverConfiguration(DriverType.EDGE),DriverType.EDGE);
            default:
                log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
                System.exit(1);
        }
        return null;
    }


    public static DriverHandler createDriver (WebDriverConfiguration configuration ) {


        switch ( configuration.getDriverType() ) {
            case CHROME:
                log.info("Creating a Chrome istance...");
                if ( configuration.getDriverType() == DriverType.CHROME ) {
                    if ( configuration.getChromeDriverPath() != null ) {
                        System.setProperty("webdriver.chrome.driver", configuration.getChromeDriverPath());
                    }
                    if ( configuration.getChromePath()  != null ) {
                        ChromeOptions chromeOptions = new ChromeOptions()
                                .setBinary(configuration.getChromePath());
                    }
                }
                return new DriverHandler(new ChromeDriver(),configuration,DriverType.CHROME);
            case FIREFOX:
                log.info("Creating a Firefox istance...");
                if ( configuration.getDriverType() == DriverType.FIREFOX ) {
                    if ( configuration.getFirefoxDriverPath() != null ) {
                        System.setProperty("webdriver.gecko.driver", configuration.getFirefoxDriverPath());
                    }
                    if ( configuration.getFirefoxPath() != null ) {
                        FirefoxOptions firefoxOptions = new FirefoxOptions()
                                .setBinary(configuration.getFirefoxPath());
                    }
                }
                return new DriverHandler(new FirefoxDriver(),configuration,DriverType.FIREFOX);
            case EDGE:
                log.info("Creating a Edge istance...");
                if ( configuration.getDriverType() == DriverType.FIREFOX ) {
                    if ( configuration.getEdgeDriverPath() != null ) {
                        System.setProperty("webdriver.edge.driver", configuration.getEdgeDriverPath());
                    }
                    if ( configuration.getEdgePath() != null ) {
                        EdgeOptions edgeOptions = new EdgeOptions()
                                .setBinary(configuration.getEdgePath());
                    }
                }
                return new DriverHandler(new EdgeDriver(),configuration,DriverType.EDGE);
            default:
                log.error("Driver Type is not valid, Chrome, Firefox or Edge are the only supported for now, exiting...");
                System.exit(1);
        }
        return null;
    }


}
