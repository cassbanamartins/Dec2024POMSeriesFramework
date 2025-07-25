package com.qa.opencart.factory;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactoryBeforeDockerGrid {

    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public static String highlight;

    public static final Logger log = LogManager.getLogger(DriverFactoryBeforeDockerGrid.class);

    /**
     * This method is used to init the driver on the basis of given browser name
     *
     * @param //browserName
     */
    //public WebDriver initDriver(String browserName)
    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser");
        // System.out.println("browser name : " + browserName);
        log.info("browser name : " + browserName);
        ChainTestListener.log("browser name: " + browserName);
        optionsManager = new OptionsManager(prop);

        highlight = prop.getProperty("highlight");

        switch (browserName.toLowerCase().trim()) {
            case "chrome":
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "edge":
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;

            default:
                //System.out.println("plz pass the valid browser name..." + browserName);
                log.error("plz pass the valid browser name..." + browserName);
                throw new BrowserException("===INVALID BROWSER===");
        }

        //driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
//        driver.get(prop.getProperty("url"));
//        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
//        return driver;

        getDriver().get(prop.getProperty("url"));// login page url
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();
    }


    /**
     * getDriver: get the local thready copy of the driver
     */

    public static WebDriver getDriver() {
        return tlDriver.get();
    }


    // mvn clean install -Denv="stage"
    public Properties initProp() {

        // reading
        String envName = System.getProperty("env");
        FileInputStream ip = null;
        prop = new Properties();

        try {
            if (envName == null) {
                //System.out.println("env is null, hence running the tests on QA env by default...");
                log.warn("env is null, hence running the tests on QA env by default...");
                ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
            } else {
                //System.out.println("Running tests on env: " + envName);
                log.info("Running tests on env: " + envName);
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
                        break;

                    default:
                        log.error("===INVALID ENV NAME==== : " + envName);
                        throw new FrameworkException("===INVALID ENV NAME==== : " + envName);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
// from mvn clean install -Denv="qa" -
    // it will call pom.xml and check the plugins compile for java code and surefire to run test
    // then pom.xml will call testng.xml
    // then testng.xml will call the baseTest
    // basetest to specific class test e.g logintest
    //logintest to loginPage object
    // login page object to elementUtil


    /**
     * takescreenshot
     */

    public static File getScreenshotFile() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
        return srcFile;
    }

    public static byte[] getScreenshotByte() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

    }

    public static String getScreenshotBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

    }

}