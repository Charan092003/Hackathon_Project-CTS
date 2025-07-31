package com.cts.miniproject.seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.cts.miniproject.browserutils.BrowserFactory.driver;

public class Wait {
    public static void implicitlyWait(WebDriver driver, int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
    public static void waitForElement(WebDriver driver, By by, int time){
        WebDriverWait wdw=new WebDriverWait(driver,Duration.ofSeconds(time));
        wdw.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}
