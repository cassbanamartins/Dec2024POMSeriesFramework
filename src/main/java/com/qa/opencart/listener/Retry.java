package com.qa.opencart.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTry = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (!iTestResult.isSuccess()) {//check if test did not succeed i.e failed

            if (count < maxTry) { // //check if maxTry count is reached i.e has it reach the number of time to retry
                count++;  // increase the MaxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE); // mark test as failed
                return true; //Tell TestNG to rerun the test

            } else {
                iTestResult.setStatus(ITestResult.FAILURE); // if maxTry count is reached, mark test as failed

            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS); // if test Passes, mark test as Passed
        }
        return false;
    }
}
