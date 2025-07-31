package com.cts.miniproject.seleniumutils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {
    public void pressEnter(WebDriver driver){
        Actions a=new Actions(driver);
        a.sendKeys(Keys.ENTER).perform();
    }
}
