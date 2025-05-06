package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;


public class RegistrationPageTest extends BaseTest {

    public static final Logger log = LogManager.getLogger(RegistrationPageTest.class);

    @BeforeClass
    public void registerSetup() {
        registrationPage = loginPage.navigateToRegistrationPage();
    }

    @DataProvider
    public Object[][] getUserRegistrationTestData() {
        return new Object[][]{
                {"AAAAAA", "XL", "9890989000", "Viisoa090", "yes"},
                {"BBBBBB", "LL", "9890989000", "Viisoa091", "no"},
                {"CCCCCC", "LL", "9890989000", "Viisoa092", "yes"},
        };
    }

    @DataProvider
    public Object[][] getUserRegData() {
        Object[][] regData = ExcelUtil.getTestData(REGISTRATION_SHEET_NAME);
        return regData;
    }

///Before Data parameteization using data provider
//    @Test
//    public void userRegistrationTest(){
//    Assert.assertTrue(registrationPage.userRegistration("Martins","adeyeye","martinsoayeye@gmail.com","9897675767","152527@gfensa", "yes"));
//}


    @Test(dataProvider = "getUserRegistrationTestData")
    public void userRegistrationTestInCodeDataProvider(String firstName, String lastName, String telephone, String password, String subscribe) {
        Assert.assertTrue(registrationPage.userRegistration(firstName, lastName, telephone, password, subscribe));
    }


    @Test(dataProvider = "getUserRegData")
    public void userRegistrationTestOnExcel(String firstName, String lastName, String telephone, String password, String subscribe) {
        Assert.assertTrue(registrationPage.userRegistration(firstName, lastName, telephone, password, subscribe));
    }

}
