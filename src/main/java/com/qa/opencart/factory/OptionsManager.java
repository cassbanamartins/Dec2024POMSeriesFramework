package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OptionsManager {

    private Properties prop;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("---Running Test in Headless Mode---");
            chromeOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("---Running Test in incognito Mode---");
            chromeOptions.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            chromeOptions.setCapability("browserName", "chrome");
            chromeOptions.setBrowserVersion(prop.getProperty("browserversion").trim());

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("screenResolution", "1280x1024x24");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("name", prop.getProperty("testname"));
            chromeOptions.setCapability("selenoid:options", selenoidOptions);

        }
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("---Running Test in Headless Mode---");
            firefoxOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("---Running Test in incognito Mode---");
            firefoxOptions.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            firefoxOptions.setCapability("browserName", "firefox");
            firefoxOptions.setBrowserVersion(prop.getProperty("browserversion").trim());

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("screenResolution", "1280x1024x24");
            selenoidOptions.put("enableVNC", true);
           selenoidOptions.put("name", prop.getProperty("testname"));
            firefoxOptions.setCapability("selenoid:options", selenoidOptions);
        }
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("---Running Test in Headless Mode---");
            edgeOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            System.out.println("---Running Test in incognito Mode---");
            edgeOptions.addArguments("--inprivate");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            edgeOptions.setCapability("browserName", "edge");
        }
        return edgeOptions;
    }

}
