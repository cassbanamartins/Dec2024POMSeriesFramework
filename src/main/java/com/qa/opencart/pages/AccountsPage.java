package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private final By headers = By.cssSelector("div#content > h2");
    private final By searchField = By.name("search");
    private final By searchBtn = By.cssSelector("div#search button");


    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getAccPageTitle() {
        String title = elementUtil.waitForTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
        System.out.println("home page title: " + title);
        return title;
    }

    public String getAccPageURL() {
        String url = elementUtil.waitForURLContains(HOME_PAGE_FRACTIONAL_URL, DEFAULT_TIMEOUT);
        System.out.println("home page url: " + url);
        return url;
    }

    public List<String> getAccPageHeaders() {
        List<WebElement> headerList = elementUtil.getElements(headers);
        List<String> headerValList = new ArrayList<String>();
        for (WebElement e : headerList) {
            String text = e.getText();
            headerValList.add(text);

        }
        System.out.println("Account Page Headers are " + headerValList);
        return headerValList;
    }

    public SearchResultsPage doSearch(String searchKey) {
        System.out.println("Search Key is: " + searchKey);
        elementUtil.doSendKeys(searchField, searchKey);
        elementUtil.doClick(searchBtn);
        return new SearchResultsPage(driver);
    }


}
