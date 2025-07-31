package com.cts.miniproject.seleniumutils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class KeyBoardActions {

    public void clearTextBox(WebElement element){
        element.sendKeys(Keys.CONTROL+"A");
        element.sendKeys(Keys.DELETE);
    }

    public void scrollToElement(WebDriver driver, WebElement targetElement){
        Actions a = new Actions(driver);
        a.moveToElement(targetElement);
    }

}
