package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;


public class AccountsPageTest extends BaseTest {

    public static final Logger log = LogManager.getLogger(AccountsPageTest.class);

    @BeforeClass
    public void accPageSetup() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accPageTitleTest() {
        Assert.assertEquals(accountsPage.getAccPageTitle(), HOME_PAGE_TITLE);
    }

    @Test
    public void homePageURLTest() {
        Assert.assertTrue(accountsPage.getAccPageURL().contains(HOME_PAGE_FRACTIONAL_URL));
    }

    @Test
    public void accPageHeadersTest() {
        List<String> actualHeaderList = accountsPage.getAccPageHeaders();
        Assert.assertEquals(actualHeaderList, expectedHeaderList);

    }

}
