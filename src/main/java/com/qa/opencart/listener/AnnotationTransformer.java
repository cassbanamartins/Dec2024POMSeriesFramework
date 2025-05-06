package com.qa.opencart.listener;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    public void transformer(ITestAnnotation iTestAnnotation, Class testClass, Constructor testConstructor, Method testMethod){
        iTestAnnotation.setRetryAnalyzer(Retry.class);
    }
}
