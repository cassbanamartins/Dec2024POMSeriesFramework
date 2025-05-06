package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        return edgeOptions;
    }

}
