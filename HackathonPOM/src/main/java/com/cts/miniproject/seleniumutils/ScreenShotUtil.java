package com.cts.miniproject.seleniumutils;

import com.cts.miniproject.frameworkutils.CommonUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;

public class ScreenShotUtil {
    public static void takeScreenShot(WebElement element){
        TakesScreenshot tss=(TakesScreenshot)element;
        File src=tss.getScreenshotAs(OutputType.FILE);
        File dest=new File("screenshots/"+ CommonUtil.getCurrentDate()+".png");
        try{
            FileUtils.copyFile(src,dest);
        }
        catch(Exception e){

        }
    }
    public static void takeScreenShot(WebDriver driver){
        TakesScreenshot tss=(TakesScreenshot)driver;
        File src=tss.getScreenshotAs(OutputType.FILE);
        File dest=new File("screenshots/"+ CommonUtil.getCurrentDate()+".png");
        try{
            FileUtils.copyFile(src,dest);
        }
        catch(Exception e){

        }
    }
    public static void takeScreenShot(SearchContext context){
        TakesScreenshot tss=(TakesScreenshot)context;
        File src=tss.getScreenshotAs(OutputType.FILE);
        File dest=new File("screenshots/"+ CommonUtil.getCurrentDate()+".png");
        try{
            FileUtils.copyFile(src,dest);
        }
        catch(Exception e){

        }
    }

    public static String takeScreenShot(WebDriver driver, String name) {
        TakesScreenshot tss=(TakesScreenshot)driver;
        File src=tss.getScreenshotAs(OutputType.FILE);
        File dest=new File("screenshots/"+ CommonUtil.getCurrentDate()+".png");
        src.renameTo(dest);
        return name;
    }
}
