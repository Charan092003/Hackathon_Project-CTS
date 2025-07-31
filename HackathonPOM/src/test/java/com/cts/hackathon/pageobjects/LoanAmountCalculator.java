package com.cts.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoanAmountCalculator extends BasePage{

    public LoanAmountCalculator(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
        initializeUItxtList();
        initializeUISliderList();
    }

    @FindBy(id = "loanemi")
    WebElement txt_EMI;

    @FindBy(id = "loaninterest")
    WebElement txt_InterestRate;

    @FindBy(id = "loanterm")
    WebElement txt_LoanTenure;

    @FindBy(id = "loanfees")
    WebElement txt_FeesAndCharge;

    @FindBy(id = "loanemislider")
    WebElement slider_EMI;

    @FindBy(id = "loaninterestslider")
    WebElement slider_InterestRate;

    @FindBy(id = "loantermslider")
    WebElement slider_LoanTenure;

    @FindBy(id = "loanfeesslider")
    WebElement slider_FeesAndCharge;

    List<WebElement> uiElements = new ArrayList<>();
    List<WebElement> sliderValues = new ArrayList<>();

    private void initializeUItxtList() {
        uiElements.add(txt_EMI);
        uiElements.add(txt_InterestRate);
        uiElements.add(txt_LoanTenure);
        uiElements.add(txt_FeesAndCharge);

    }
    public void initializeUISliderList() {
        sliderValues.add(slider_EMI);
        sliderValues.add(slider_InterestRate);
        sliderValues.add(slider_LoanTenure);
        sliderValues.add(slider_FeesAndCharge);
    }

    public boolean validateLoanAmountTxtElements() {
        EMICalculatorNew emiCalculatorNew = new EMICalculatorNew(driver);
        return emiCalculatorNew.validateTxtElement(uiElements);
    }

    public boolean validateLoanAmountSliderElements() {
        EMICalculatorNew emiCalculatorNew = new EMICalculatorNew(driver);
        return emiCalculatorNew.validateSliderElement(sliderValues);
    }

}
