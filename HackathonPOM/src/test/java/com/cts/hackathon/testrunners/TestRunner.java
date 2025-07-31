package com.cts.hackathon.testrunners;

import com.cts.hackathon.pageobjects.*;
import com.cts.miniproject.browserutils.BrowserFactory;
import com.cts.miniproject.frameworkutils.CommonUtil;
import com.cts.miniproject.frameworkutils.DataProvider;
import com.cts.miniproject.frameworkutils.PropertiesFileReader;
import com.cts.miniproject.seleniumutils.ScreenShotUtil;
import com.cts.miniproject.testlistener.MyListenerCombined;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(MyListenerCombined.class)
public class TestRunner {
    public static WebDriver driver = null;
    String bn = null;
    String url = null;
    String wr;
    String remoteip;
    BasePage basePage = null;
    CarLoan carloan=null;
    HomeLoan homeLoan = null;
    PersonalLoan personalLoan=null;
    EMICalculatorNew emiCalculatorNew=null;
    LoanAmountCalculator loanAmountCalculator=null;
    LoanTenureCalculator loanTenureCalculator=null;

    @BeforeMethod
    public void preSetup() throws Exception {

        try {
            bn = PropertiesFileReader.getPropertyValue("config", "browsername");
            wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
            remoteip = PropertiesFileReader.getPropertyValue("config", "hubip");
            url = PropertiesFileReader.getPropertyValue("config", "url");


            driver = BrowserFactory.getBrowser(bn,url);
            BrowserFactory.openurl(url);
            basePage = new BasePage(driver);
            carloan = new CarLoan(driver);
            homeLoan = new HomeLoan(driver);
            personalLoan=new PersonalLoan(driver);
            emiCalculatorNew=new EMICalculatorNew(driver);
            loanAmountCalculator=new LoanAmountCalculator(driver);
            loanTenureCalculator=new LoanTenureCalculator(driver);

        } catch (Exception e) {
            throw e;
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 0)
    public void launchEMICalcPage(String expectedTitle) throws Exception {
        try {
            String title= basePage.getPageTitle();
            Assert.assertEquals(title,expectedTitle,"Page Title mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 1)
    public void openPersonalLoanCalc(String expectedLabel) throws Exception {
        try {
            basePage.clickPersonalLoan();
            String label=personalLoan.verifyPersonalLoanPage();
            Assert.assertEquals(label,expectedLabel,"page did not switch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 2)
    public void openCarLoanCalc(String expectedLabel) throws Exception {
        try {
            basePage.clickCarLoan();
            String label=carloan.verifyCarLoanPage();
            Assert.assertEquals(label,expectedLabel,"page did not switch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 3)
    public void calculateCarLoanEMI(String amount,String interestRate,String tenure,String expectedEMI) throws Exception {
        try {
            carloan.clickCarLoan();
            carloan.setLoanAmount(amount);
            carloan.setInterest(interestRate);
            carloan.setLoanTenure(tenure);
            carloan.clickYearButton();
            String emi=carloan.verifyCalculation();
            Assert.assertEquals(emi,expectedEMI,"mismatch of EMI");

        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 4)
    public void viewFirstMonthBreakdown(String amount,String interestRate,String tenure,String expectedPrincipleAmount,String expectedInterestAmount) throws Exception {
        try {
            carloan.clickCarLoan();
            carloan.setLoanAmount(amount);
            carloan.setInterest(interestRate);
            carloan.setLoanTenure(tenure);
            carloan.clickYearButton();
            CommonUtil.sureWait(3);
            carloan.clickYear2025();
            String principleAmount=carloan.getPrincipleAmount();
            String interestAmount=carloan.getInterestAmount();
            Assert.assertEquals(principleAmount,expectedPrincipleAmount,"principle amount mismatch");
            Assert.assertEquals(interestAmount,expectedInterestAmount,"interest amount mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 5)
    public void updateInterestRecalculate(String amount,String interestRate,String tenure,String expectedPrincipleAmount,String expectedInterestAmount) throws Exception {
        try {
            carloan.clickCarLoan();
            carloan.setLoanAmount(amount);
            carloan.setInterest(interestRate);
            carloan.setLoanTenure(tenure);
            carloan.clickYearButton();
            CommonUtil.sureWait(3);
            carloan.clickYear2025();
            String principleAmount=carloan.getPrincipleAmount();
            String interestAmount=carloan.getInterestAmount();
            Assert.assertEquals(principleAmount,expectedPrincipleAmount,"principle amount mismatch");
            Assert.assertEquals(interestAmount,expectedInterestAmount,"interest amount mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 6)
    public void updateTenureRecalculate(String amount,String interestRate,String tenure,String expectedPrincipleAmount,String expectedInterestAmount) throws Exception {
        try {
            carloan.clickCarLoan();
            carloan.setLoanAmount(amount);
            carloan.setInterest(interestRate);
            carloan.setLoanTenure(tenure);
            CommonUtil.sureWait(3);
            carloan.clickYear2025();
            String principleAmount=carloan.getPrincipleAmount();
            String interestAmount=carloan.getInterestAmount();
            Assert.assertEquals(principleAmount,expectedPrincipleAmount,"principle amount mismatch");
            Assert.assertEquals(interestAmount,expectedInterestAmount,"interest amount mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 7)
    public void updateLoanAmountRecalculate(String amount,String interestRate,String tenure,String expectedPrincipleAmount,String expectedInterestAmount) throws Exception {
        try {
            carloan.clickCarLoan();
            carloan.setLoanAmount(amount);
            carloan.setInterest(interestRate);
            carloan.setLoanTenure(tenure);
            CommonUtil.sureWait(3);
            carloan.clickYear2025();
            String principleAmount=carloan.getPrincipleAmount();
            String interestAmount=carloan.getInterestAmount();
            Assert.assertEquals(principleAmount,expectedPrincipleAmount,"principle amount mismatch");
            Assert.assertEquals(interestAmount,expectedInterestAmount,"interest amount mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 8)
    public void openHomeLoanCalc(String expectedLabel) throws Exception {
        try {
            basePage.clickHomeLoan();
            String label=homeLoan.verifyHomeLoanPage();
            Assert.assertEquals(label,expectedLabel,"page did not switch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 9)
    public void calculateHomeLoanEMI(String amount,String interestRate,String tenure) throws Exception {
        try {
            homeLoan.setHomeLoanAmount(amount);
            homeLoan.setInterestRate(interestRate);
            homeLoan.setLoanTenure(tenure);
            homeLoan.clickYear();
            boolean checkTable=homeLoan.checkTable();
            Assert.assertTrue(checkTable,"Table not enabled");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 10)
    public void verifyYearlyTableRowCount(String amount,String interestRate,String tenure, String rows) throws Exception {
        try {
            homeLoan.setHomeLoanAmount(amount);
            homeLoan.setInterestRate(interestRate);
            homeLoan.setLoanTenure(tenure);
            homeLoan.clickYear();
            int expectedRows = Integer.parseInt(rows);
            int rowNo =  homeLoan.noOfRows();
            Assert.assertEquals(rowNo, expectedRows,"row count mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 11)
    public void exportEMITableToExcel(String amount,String interestRate,String tenure) throws Exception {
        try {
            homeLoan.setHomeLoanAmount(amount);
            homeLoan.setInterestRate(interestRate);
            homeLoan.setLoanTenure(tenure);
            homeLoan.clickYear();
            boolean table=homeLoan.printYearTableData();
            Assert.assertTrue(table,"error while exporting data");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 12)
    public void checkEMICalcInputFields() throws Exception {
        emiCalculatorNew.openingLoanCalculatorPage();
        boolean emiTxtElements = emiCalculatorNew.validateEmiTxtElements();
        Assert.assertTrue(emiTxtElements, "Input Boxes Element Mismatch in EMI Calculator");
        try {
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 13)
    public void checkEMICalcSliders() throws Exception {
        try {
            emiCalculatorNew.openingLoanCalculatorPage();
            boolean emiSliderElements = emiCalculatorNew.validateEmiSliderElements();
            Assert.assertTrue(emiSliderElements, "Slider Element Mismatch in EMI Calculator");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 14)
    public void checkLoanAmountInputFields() throws Exception {
        try {
            loanTenureCalculator.openingLoanCalculatorPage();
            loanAmountCalculator.clickLoanAmountCalculatorPage();
            boolean loanAmountTxtElements = loanAmountCalculator.validateLoanAmountTxtElements();
            Assert.assertTrue(loanAmountTxtElements, "Input Boxes Element Mismatch in Loan Amount Calculator");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 15)
    public void checkLoanAmountSliders() throws Exception {
        try {
            loanTenureCalculator.openingLoanCalculatorPage();
            loanAmountCalculator.clickLoanAmountCalculatorPage();
            boolean loanAmountSliderElements = loanAmountCalculator.validateLoanAmountSliderElements();
            Assert.assertTrue(loanAmountSliderElements, "Slider Element Mismatch in Loan Amount Calculator");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 16)
    public void checkLoanTenureInputFields() throws Exception {

        try {
            loanTenureCalculator.openingLoanCalculatorPage();
            loanTenureCalculator.clickLoanTenureCalculatorPage();
            boolean loanTenureTxtElements = loanTenureCalculator.validateLoanTenureSliderElements();
            Assert.assertTrue(loanTenureTxtElements, "Input Boxes Element Mismatch in Loan Tenure Calculator");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 17)
    public void checkLoanTenureSliders() throws Exception {
        try {
            loanTenureCalculator.openingLoanCalculatorPage();
            loanTenureCalculator.clickLoanTenureCalculatorPage();
            boolean loanTenureSliderElements = loanTenureCalculator.validateLoanTenureSliderElements();
            Assert.assertTrue(loanTenureSliderElements, "Slider element mismatch in Loan Tenure Calculator");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @Test(dataProvider="mp", dataProviderClass= DataProvider.class, priority = 18)
    public void verifyTenureScaleChange() throws Exception {
        try {
            emiCalculatorNew.openingLoanCalculatorPage();
            boolean sliderCheck = emiCalculatorNew.compareChangeInSlider();
            Assert.assertTrue(sliderCheck, "Slider change Mismatch");
        } catch(Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void postTest(){
        CommonUtil.sureWait(3);
        driver.close();
        driver.quit();
    }

}
