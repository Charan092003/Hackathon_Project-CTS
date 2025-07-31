package com.cts.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu-item-dropdown-2696")
    WebElement dropdown_loanCalculatorDropDown;

    @FindBy(xpath = "//li[@id='menu-item-2423']/a")
    WebElement btn_loanCalculatorOption;

    @FindBy(xpath = "//li[@id='car-loan']/a")
    WebElement btn_carLoan;

    @FindBy(xpath = "//li[@id='home-loan']/a")
    WebElement btn_homeLoan;

    @FindBy(xpath = "//li[@id='personal-loan']/a")
    WebElement btn_personalLoan;

    @FindBy(xpath = "//li[@id='loan-amount-calc']/a[1]")
    WebElement btn_loanAmountCalculator;

    @FindBy(xpath = "//li[@id='loan-tenure-calc']/a[1]")
    WebElement btn_loanTenureCalculator;

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void openingLoanCalculatorPage(){
        dropdown_loanCalculatorDropDown.click();
        btn_loanCalculatorOption.click();
    }

    public void clickLoanAmountCalculatorPage(){
        btn_loanAmountCalculator.click();
    }

    public void clickLoanTenureCalculatorPage(){
        btn_loanTenureCalculator.click();
    }

    public void clickCarLoan(){
        btn_carLoan.click();
    }

    public void clickHomeLoan(){
        btn_homeLoan.click();
    }

    public void clickPersonalLoan(){
        btn_personalLoan.click();
    }


}
