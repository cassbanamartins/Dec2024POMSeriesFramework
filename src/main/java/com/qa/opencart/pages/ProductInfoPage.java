package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static com.qa.opencart.constants.AppConstants.*;


public class ProductInfoPage {

    WebDriver driver;
    private ElementUtil elementUtil;

    private final By productHeader = By.tagName("h1");
    private final By productImages = By.cssSelector("ul.thumbnails img");
    private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

    private Map<String, String> productMap;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    public String getProductHeader() {
        String header = elementUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
        System.out.println("Product Header :" + header);
        return header;
    }

    public int getProductImagesCount() {
        int imageCount = elementUtil.waitForAllElementsVisible(productImages, MEDIUM_DEFAULT_TIMEOUT).size();
        System.out.println("Total image count: " + imageCount);
        return imageCount;
    }

    // HaspMap is totally unordered, no sequence follow, i.e it does not maintain order
    // LinkedHashMap - maintain insertion/adding order
    //  TreeMap- maintain sorted order for the key
    public Map<String, String> getProductDetailsMap() {
        //productMap = new HashMap<String, String>();
        //productMap = new LinkedHashMap<String, String>();
        productMap = new TreeMap<String, String>();
        productMap.put("productheader", getProductHeader());
        productMap.put("productimages", String.valueOf(getProductImagesCount()));
        getProductMetaData();
        getProductPriceData();
        System.out.println("Full product Details: " + productMap);
        return productMap;
    }

    //    Hasmap cos its key value pair
//    Brand: Apple
//    Product Code: Product 18
//    Reward Points: 800
//    Availability: Out Of Stock
    private void getProductMetaData() {
        List<WebElement> metaList = elementUtil.waitForAllElementsVisible(productMetaData, DEFAULT_TIMEOUT);
        for (WebElement e : metaList) {
            //get all the list from metalist and store the text into meteListText
            String metaData = e.getText();
            // we need to split and trim the data since its coming back with Key:Value paid
            // then store it inside an array
            String[] meta = metaData.split(":");
            //Split into mean the first part will have index 0 and other part index 1
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            productMap.put(metaKey, metaValue);
        }

    }

//    $2,000.00
//    Ex Tax: $2,000.00

    private void getProductPriceData() {
        //getting the whole price list
        List<WebElement> priceList = elementUtil.waitForAllElementsVisible(productPriceData, DEFAULT_TIMEOUT);
        // the list return 2 , index 0 and 1
        String productPrice = priceList.get(0).getText();
        //Split into mean the first part will have index 0 and other part index 1, so i need the index 1 for comparison
        String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productprice", productPrice);
        productMap.put("extaxprice", exTaxPrice);
    }
}
