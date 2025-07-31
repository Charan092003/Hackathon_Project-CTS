package com.cts.hackathon.pageobjects;

import com.cts.miniproject.frameworkutils.CommonUtil;
import com.cts.miniproject.seleniumutils.KeyBoardActions;
import com.cts.miniproject.seleniumutils.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoan extends BasePage{

    public CarLoan(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "loanamount")
    WebElement txt_carLoanAmount;

    @FindBy(id = "loaninterest")
    WebElement txt_Interest;

    @FindBy(id = "loanterm")
    WebElement txt_loanTenure;

    @FindBy(id = "year2025")
    WebElement btn_year2025;

    @FindBy(id = "yearheader")
    WebElement displaytxt_scrollPoint;

    @FindBy(xpath = "//*[@id='monthyear2025']/td/div/table/tbody/tr[1]/td[2]")
    WebElement displaytxt_JulyPrinciple;

    @FindBy(xpath = "//*[@id='monthyear2025']/td/div/table/tbody/tr[1]/td[3]")
    WebElement displaytxt_JulyInterest;

    @FindBy(xpath = "//input[@id='loanyears']/..")
    WebElement btn_yearButton;

    @FindBy(xpath = "//div[@id='emicalculatorinnerform']/div[1]/label")
    WebElement txt_carLoan;

    @FindBy(xpath = "//div[@id='emiamount']/p/span")
    WebElement txt_EMI;

//    KeyBoardActions keyBoardActions = new KeyBoardActions();
    KeyBoardActions keyBoardActions = new KeyBoardActions();

    public void setLoanAmount(String carLoanAmount) {
        Wait.waitForVisibility(txt_carLoanAmount);
        txt_carLoanAmount.click();
        keyBoardActions.clearTextBox(txt_carLoanAmount);
        txt_carLoanAmount.sendKeys(carLoanAmount);
    }
    public void setInterest(String carLoanInterest){
        Wait.waitForVisibility(txt_Interest);
        txt_Interest.click();
        keyBoardActions.clearTextBox(txt_Interest);
        txt_Interest.sendKeys(carLoanInterest);
    }

    public void setLoanTenure(String carLoanTenure){
        Wait.waitForVisibility(txt_loanTenure);
        txt_loanTenure.click();
        keyBoardActions.clearTextBox(txt_loanTenure);
        txt_loanTenure.sendKeys(carLoanTenure);
    }

    public void clickYearButton(){
        btn_yearButton.click();
    }

    public void clickYear2025(){
        btn_yearButton.click();
        CommonUtil.sureWait(2);
        Wait.waitForClickability(btn_year2025);
        btn_year2025.click();
    }

    public String getPrincipleAmount(){
        Wait.waitForVisibility(displaytxt_JulyPrinciple);
        return displaytxt_JulyPrinciple.getText();
    }

    public String getInterestAmount(){
        Wait.waitForVisibility(displaytxt_JulyInterest);
        return displaytxt_JulyInterest.getText();
    }

    public String verifyCarLoanPage(){
        return txt_carLoan.getText();
    }

    public String verifyCalculation(){
        return txt_EMI.getText();

    }
}
