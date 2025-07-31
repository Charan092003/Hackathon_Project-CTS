package com.cts.miniproject.testlistener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cts.miniproject.seleniumutils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

//ItestListenr interface has all the methods that a event listner class is going to need
public class MyListenerCombined implements ITestListener {

    //this generates html report
    private static ExtentReports extent;
    //if multiple test cases are running to keep them safe we implement multithreading using this
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final Map<String, WebDriver> driverMap = new HashMap<>();

    static {
        String reportPath = "test-output/MergedExtentReport/ExtentReport.html";
        //gives the graphs and other visual reps in the report
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Emi Calc Test Suite");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Charan");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        //this gets the name of class and method of the test in class::method format
        ExtentTest test = extent.createTest(
                result.getTestClass().getRealClass().getSimpleName() + " :: " + result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //this gives pass status for case in report file
        testThread.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //updates fail, takes screenshot, and gives the reason why it failed
        Object testClassInstance = result.getInstance();
        WebDriver driver = getDriverFromTestClass(testClassInstance);
        String screenshotPath = ScreenShotUtil.takeScreenShot(driver, result.getName());
        testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        testThread.get().addScreenCaptureFromPath(screenshotPath);
        System.out.println("Screenshot saved at: " + screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // we flush all the methods above to the report at the end
        extent.flush();
    }

    // Utility method to extract driver using reflection
    private WebDriver getDriverFromTestClass(Object testInstance) {
        try {
            return (WebDriver) testInstance.getClass().getField("driver").get(testInstance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}