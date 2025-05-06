package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil elementUtil;


    // do not use Static keyword for locators as it will prevent parallel execution
    // 1. private By locators: OR
    private final By email = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By forgotPwdLink = By.linkText("Forgotten Password");
    private final By registerLink = By.linkText("Register");

    // 2. public page constr...
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    // 3. public page actions/methods
    @Step("Checking Login Page Title")
    public String getLoginPageTitle() {
        String title = elementUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
        // String title = driver.getTitle();
        System.out.println("login page title: " + title);
        return title;
    }

    public String getLoginPageURL() {
        String url = elementUtil.waitForURLContains(LOGIN_PAGE_FRACTIONAL_URL, DEFAULT_TIMEOUT);
        // String url = driver.getCurrentUrl();
        System.out.println("login page url: " + url);
        return url;
    }

    public boolean isForgotPwdLinkExist() {
        return elementUtil.isElementDisplayed(forgotPwdLink);
        //return driver.findElement(forgotPwdLink).isDisplayed();
    }

    @Step("login with valid username: {0} and password: {1}")
    public AccountsPage doLogin(String username, String pwd) {
        System.out.println("user credentials: " + username + ":" + pwd);
        elementUtil.waitForElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(username);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.doClick(loginBtn);
        //driver.findElement(email).sendKeys(username);
        //driver.findElement(password).sendKeys(pwd);
        //driver.findElement(loginBtn).click();

//        String title = elementUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
//        //String title = driver.getTitle();
//        System.out.println("accounts page title: " + title);
//        return title;

        return new AccountsPage(driver);
    }

    @Step("Navigating to Registration Page")
    public RegistrationPage navigateToRegistrationPage() {
        elementUtil.clickWithWait(registerLink, DEFAULT_TIMEOUT);
        //this method should return the page it will land into
        return new RegistrationPage(driver);
    }
}