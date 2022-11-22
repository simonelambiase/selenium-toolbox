package utils;

import driver.DriverType;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

public class FileUtils {

    public static void copyPackagedResources () {
        String[] resources = {"config_default.properties","log4j2.xml","chromedriver.exe","geckodriver.exe","msedgedriver.exe","dummy1.html"};
        for ( int i = 0; i < resources.length; i++ ) {
            InputStream is = FileUtils.class.getResourceAsStream("resources/" + resources[i]);
            System.out.println(is);
        }
    }
}
