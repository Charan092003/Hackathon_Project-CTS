package com.cts.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoanTenureCalculator extends BasePage{

    public LoanTenureCalculator(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        initializeTxtList();
        initializeLoanList();
    }

    @FindBy(id = "loanamount")
    WebElement txt_LoanAmount;

    @FindBy(id = "loanemi")
    WebElement txt_EMI;

    @FindBy(id = "loaninterest")
    WebElement txt_InterestRate;

    @FindBy(id = "loanfees")
    WebElement txt_FeesAndCharge;

    @FindBy(id = "loanamountslider")
    WebElement slider_LoanAmount;

    @FindBy(id = "loanemislider")
    WebElement slider_Emi;

    @FindBy(id = "loaninterestslider")
    WebElement slider_InterestRate;

    @FindBy(id = "loanfeesslider")
    WebElement slider_FeesAndCharge;

    List<WebElement> uiElements = new ArrayList<>();
    List<WebElement> sliderValues = new ArrayList<>();

    private void initializeTxtList() {
        uiElements.add(txt_LoanAmount);
        uiElements.add(txt_EMI);
        uiElements.add(txt_InterestRate);
        uiElements.add(txt_FeesAndCharge);

    }
    public void initializeLoanList() {
        sliderValues.add(slider_LoanAmount);
        sliderValues.add(slider_Emi);
        sliderValues.add(slider_InterestRate);
        sliderValues.add(slider_FeesAndCharge);
    }
    EMICalculatorNew emiCalculatorNew = new EMICalculatorNew(driver);

    public boolean validateLoanTenureTxtElements() {
        return emiCalculatorNew.validateTxtElement(uiElements);
    }

    public boolean validateLoanTenureSliderElements() {
        return emiCalculatorNew.validateSliderElement(sliderValues);
    }

}
