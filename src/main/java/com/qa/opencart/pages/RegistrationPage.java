package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;


public class RegistrationPage {

    WebDriver driver;
    private ElementUtil elementUtil;

    private final By firstNameField = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By emailField = By.id("input-email");
    private final By telephoneField = By.id("input-telephone");
    private final By passwordField = By.id("input-password");
    private final By confirmPasswordField = By.id("input-confirm");
    private final By subscribeYes = By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[position()=1]");
    private final By subscribeNo = By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[position()=2]");
    private final By agreeCheckboxField = By.name("agree");
    private final By continueBtnField = By.xpath("//input[@type='submit' and @value='Continue']");
    private final By welcomeMessageField = By.cssSelector("div#content h1");
    private final By welcomeCongratulationField = By.tagName("p");
    private final By continueBtnOnSuccessField = By.linkText("Continue");
    private final By logOutButton = By.linkText("Logout");
    private final By registerLink = By.linkText("Register");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public boolean userRegistration(String firstName, String lastName, String telephone, String password, String subscribe) {
        elementUtil.waitForElementVisible(firstNameField, DEFAULT_TIMEOUT).sendKeys(firstName);
        elementUtil.doSendKeys(this.lastName, lastName);
        elementUtil.doSendKeys(emailField, StringUtil.getRandomEmailId());
        elementUtil.doSendKeys(telephoneField, telephone);
        elementUtil.doSendKeys(passwordField, password);
        elementUtil.doSendKeys(confirmPasswordField, password);

        if (subscribe.equalsIgnoreCase("yes")) {
            elementUtil.doClick(subscribeYes);
        } else {
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doClick(agreeCheckboxField);
        elementUtil.doClick(continueBtnField);

        if (elementUtil.waitForElementVisible(welcomeMessageField, MEDIUM_DEFAULT_TIMEOUT).getText().contains(REGISTRATION_SUCCESS_MSG)) {
            elementUtil.doClick(logOutButton);
            elementUtil.doClick(registerLink);
            return true;
        }
        return false;
    }
}
