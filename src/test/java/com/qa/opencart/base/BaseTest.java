package com.qa.opencart.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.pages.*;
import com.qa.opencart.utils.LogUtil;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.qa.opencart.factory.DriverFactory;

import java.util.Properties;

//@Listeners(ChainTestListener.class) --instead of here. put it inside the testng.xml
public class BaseTest {

    WebDriver driver;

    DriverFactory df;
    protected Properties prop;
    protected LoginPage loginPage;
    protected AccountsPage accountsPage;
    protected SearchResultsPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    protected RegistrationPage registrationPage;

    public static final Logger log = LogManager.getLogger(BaseTest.class);

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        df = new DriverFactory();
        prop = df.initProp();
        //driver = df.initDriver("chrome");
        //browserName is passed from the .xml file
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }
        // ChainTestListener.log("Properties used: " + prop);

        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod //will be running after each @test method
    public void attachScreenshot(ITestResult result) {
        if (!result.isSuccess()) {//only for failure test cases -- true
            ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
        }

        //ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");


    }

    @Description("Closing the Browser")
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}