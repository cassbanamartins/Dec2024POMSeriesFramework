package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;


public class SearchResultsPage {

    WebDriver driver;
    private ElementUtil elementUtil;

    private final By resultsProduct = By.cssSelector("div.product-thumb");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    public int getResultsProductCount() {
        int searchCount = elementUtil.waitForAllElementsVisible(resultsProduct, DEFAULT_TIMEOUT).size();
        System.out.println("Total number of search products: " + searchCount);
        return searchCount;
    }

    public ProductInfoPage selectProduct(String productName) {
        System.out.println("Product name: " + productName);
        elementUtil.doClick(By.linkText(productName));
        return new ProductInfoPage(driver);
    }

}
