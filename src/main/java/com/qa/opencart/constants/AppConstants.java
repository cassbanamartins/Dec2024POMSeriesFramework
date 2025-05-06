package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
    public static final int DEFAULT_TIMEOUT = 5;
    public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
    public static final int LONG_DEFAULT_TIMEOUT = 15;
    public static final String LOGIN_PAGE_TITLE = "Account Login11";
    public static final String HOME_PAGE_TITLE = "My Account";
    public static final String LOGIN_PAGE_FRACTIONAL_URL = "route=account/login";
    public static final String HOME_PAGE_FRACTIONAL_URL = "route=account/account";
    public static final String REGISTRATION_SUCCESS_MSG = "Your Account Has Been Created!";


    //Creating list of constant //another way of creating array list
    public static List<String> expectedHeaderList = List.of("My Account","My Orders","My Affiliate Account","Newsletter");

// *************** Sheet Name********************
public static final String REGISTRATION_SHEET_NAME = "RegistrationData";
public static final String PRODUCT_DATA_SHEET_NAME = "ProductData";




}
