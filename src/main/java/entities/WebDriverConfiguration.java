package entities;

import driver.DriverType;

public class WebDriverConfiguration {

    private String chromePath;
    private String edgePath;

    private String firefoxPath;
    private String chromeDriverPath;
    private String edgeDriverPath;
    private String firefoxDriverPath;
    private DriverType driverType;


    public WebDriverConfiguration(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getChromePath() {
        return chromePath;
    }

    public void setChromePath(String chromePath) {
        this.chromePath = chromePath;
    }

    public String getEdgePath() {
        return edgePath;
    }

    public void setEdgePath(String edgePath) {
        this.edgePath = edgePath;
    }

    public String getFirefoxPath() {
        return firefoxPath;
    }

    public void setFirefoxPath(String firefoxPath) {
        this.firefoxPath = firefoxPath;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getEdgeDriverPath() {
        return edgeDriverPath;
    }

    public void setEdgeDriverPath(String edgeDriverPath) {
        this.edgeDriverPath = edgeDriverPath;
    }

    public String getFirefoxDriverPath() {
        return firefoxDriverPath;
    }

    public void setFirefoxDriverPath(String firefoxDriverPath) {
        this.firefoxDriverPath = firefoxDriverPath;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }
}
