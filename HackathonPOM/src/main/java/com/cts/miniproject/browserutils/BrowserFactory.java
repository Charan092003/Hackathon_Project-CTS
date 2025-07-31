package com.cts.miniproject.browserutils;

import com.cts.miniproject.seleniumutils.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class BrowserFactory {

    public static WebDriver driver;

    public static WebDriver getBrowser(String browserName, String url){
        switch(browserName.intern().toLowerCase()){
            case "chrome":
                driver=new ChromeDriver();
                break;
            case "edge":
                driver=new EdgeDriver();
                break;
            default:
                ChromeOptions co=new ChromeOptions();
                co.addArguments("--headless");
                driver=new ChromeDriver(co);
        }
        driver.manage().window().maximize();
        Wait w=new Wait();
        Wait.implicitlyWait(driver,3);
        driver.get(url);
        return driver;
    }

    public static WebDriver getBrowser(String browserName){
        switch(browserName.intern().toLowerCase()){
            case "chrome":
                driver=new ChromeDriver();
                break;
            case "edge":
                driver=new EdgeDriver();
                break;
            default:
                ChromeOptions co=new ChromeOptions();
                co.addArguments("--headless");
                driver=new ChromeDriver(co);
        }
        driver.manage().window().maximize();
        Wait w=new Wait();
        Wait.implicitlyWait(driver,3);
        return driver;
    }

    public static WebDriver getBrowser() throws NullPointerException{
        return driver;
    }

    public static void openurl(String url){

        driver.get(url);
    }

    public static WebDriver runLocal(String browserName){
        switch(browserName.intern().toLowerCase()){
            case "chrome":
                driver=new ChromeDriver();
                break;
            case "edge":
                driver=new EdgeDriver();
                break;

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;

    }
    public static WebDriver runRemote(String browserName,String ip) throws Exception{
        DesiredCapabilities ds=new DesiredCapabilities();
        ds.setBrowserName(browserName);
        switch(browserName.intern().toLowerCase()){
            case "chrome":
                driver=new RemoteWebDriver(new URL(ip+"/wd/hub"), ds);
                break;
            case "edge":
                driver=new EdgeDriver();
                break;

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.get(urlapp);
        return driver;

    }

    public static WebDriver getBrowser(String browserName,String wr,String hip) throws Exception {
        switch(wr.intern().toLowerCase()){
            case "cloud":
                driver=runRemote(browserName,hip);
                break;
            default:
                driver=runLocal(browserName);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

}
