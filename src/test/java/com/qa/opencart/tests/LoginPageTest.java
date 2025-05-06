package com.qa.opencart.tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

import static com.qa.opencart.constants.AppConstants.*;

@Feature("New Feature")
@Epic("Reviewed Epic")
@Story("Overall Story")
public class LoginPageTest extends BaseTest {

    public static final Logger log = LogManager.getLogger(LoginPageTest.class);


    @Description("login Page Title Test")
    @Severity(SeverityLevel.MINOR)
    @Owner("Martins")
    @Test(description = "Checking Login Page Title")
    public void loginPageTitleTest() {
        String actTitle = loginPage.getLoginPageTitle();
        ChainTestListener.log("Checking Login Page Title: " + actTitle);
        Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
    }

    @Test
    public void loginPageURLTest() {
        String actURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTIONAL_URL));
    }

    @Test
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Test(priority = Short.MAX_VALUE, description = "Login with Valid Credential")
    public void doLoginTest() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(accountsPage.getAccPageTitle(), HOME_PAGE_TITLE);
    }

    @Test(enabled = false)
    public void forgotPassword() {
        System.out.println("Running Login Test on xml to verify if forgot Password Test  will be executed when enabled is set to false");
    }

}