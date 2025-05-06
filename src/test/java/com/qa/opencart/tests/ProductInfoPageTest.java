package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.Map;

import static com.qa.opencart.constants.AppConstants.REGISTRATION_SHEET_NAME;

public class ProductInfoPageTest extends BaseTest {

    public static final Logger log = LogManager.getLogger(ProductInfoPageTest.class);

    @BeforeClass
    public void searchSetup() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @DataProvider // it returns 2-dimensional array
    public Object[][] getProductTestData() {
        return new Object[][]{
                {"macbook", "MacBook Pro"},
                {"macbook", "MacBook Air"},
                {"imac", "iMac"},
                {"samsung", "Samsung SyncMaster 941BW"},
                {"samsung", "Samsung Galaxy Tab 10.1"},
        };
    }

    // Arrange - Act - Assert
   /*
    // Before Parameterization
    @Test
    public void productHeaderTest() {
        searchResultsPage = accountsPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        String actualHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actualHeader, "MacBook Pro");
    }*/

    //after parameterization using dataprovider
    @Test(dataProvider = "getProductTestData")
    public void productHeaderTest(String searchKey, String productName) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        String actualHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actualHeader, productName);
    }

    @DataProvider // it returns 2-dimensional array
    public Object[][] getProductImageTestData() {
        return new Object[][]{
                {"macbook", "MacBook Pro", 4},
                {"macbook", "MacBook Air", 4},
                {"imac", "iMac", 3},
                {"samsung", "Samsung SyncMaster 941BW", 1},
                {"samsung", "Samsung Galaxy Tab 10.1", 7},
        };
    }

    @DataProvider
    public Object[][] getProductSheetData() {
        Object[][] myProductData = ExcelUtil.getTestData(PRODUCT_DATA_SHEET_NAME);
        return myProductData;
    }

    @DataProvider
    public Object[][] getProductCSVData() {
        return CSVUtil.csvData("product");
    }

    @Test(dataProvider = "getProductImageTestData")
    public void productImageCountTestInCodeDataProvider(String searchKey, String productName, int imageCount) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        int actualImageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(actualImageCount, imageCount);
    }

    @Test(dataProvider = "getProductSheetData")
    public void productImageCountTestOnExcel(String searchKey, String productName, String imageCount) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        int actualImageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(String.valueOf(actualImageCount), imageCount);
    }

    @Test(dataProvider = "getProductCSVData")
    public void productImageCountTestOnCSV(String searchKey, String productName, String imageCount) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        int actualImageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(String.valueOf(actualImageCount), imageCount);
    }

    //    Brand: Apple
    //    Product Code: Product 18
    //    Reward Points: 800
    //    Availability: Out Of Stock
    //    $2,000.00
    //    Ex Tax: $2,000.00


    // HaspMap is totally unordered, no sequence follow, i.e it does not maintain order
    // LinkedHashMap
    // TreeMap - Full product Details:
    // {Availability=Out Of Stock, Brand=Apple, Product Code=Product 18, Reward Points=800, extaxprice=$2,000.00, productheader=MacBook Pro, productimages=4, productprice=$2,000.00}

    //Test with Hard Assertions
//    @Test
//    public void productInfoTest() {
//        searchResultsPage = accountsPage.doSearch("macbook");
//        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
//        Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();
//
//        Assert.assertEquals(actualProductDetailsMap.get("productheader"), "MacBook Pro");
//        Assert.assertEquals(actualProductDetailsMap.get("productimages"), "4");
//
//        Assert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
//        Assert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
//        Assert.assertEquals(actualProductDetailsMap.get("Reward Points"), "800");
//        Assert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
//        Assert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
//        Assert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");
//
//    }

    //Test with soft Assertions
    // SoftAssert - Soft assertions are the ones in which the test execution does not stop if the test does not meet the assertion condition
    // softassertion is used when a single test has multiple assertion
    @Test
    public void productInfoTest() {
        searchResultsPage = accountsPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
        softAssert.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
        softAssert.assertEquals(actualProductDetailsMap.get("Reward Points"), "800");
        softAssert.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
        softAssert.assertEquals(actualProductDetailsMap.get("productprice"), "$2,000.00");
        softAssert.assertEquals(actualProductDetailsMap.get("extaxprice"), "$2,000.00");

        softAssert.assertAll();
    }

}
