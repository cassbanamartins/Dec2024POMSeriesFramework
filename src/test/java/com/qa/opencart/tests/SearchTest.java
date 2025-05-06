package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.SearchResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;


public class SearchTest extends BaseTest {

    public static final Logger log = LogManager.getLogger(SearchTest.class);

    @BeforeClass
    public void searchSetup() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void searchTest() {
        searchResultsPage = accountsPage.doSearch("airtel");
        int actualResultCount = searchResultsPage.getResultsProductCount();
        Assert.assertEquals(actualResultCount, 0);
    }
}
