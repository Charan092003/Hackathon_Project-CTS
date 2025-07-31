package com.cts.hackathon.pageobjects;

import com.cts.miniproject.frameworkutils.CommonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EMICalculatorNew extends BasePage {
    //WebDriver driver;


    public EMICalculatorNew(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
        initializeTxtElementList();
        initializeSliderElementList();

    }



    @FindBy(id = "loanamount")
    WebElement txt_EMICalcLoanAmount;

    @FindBy(id = "loaninterest")
    WebElement txt_InterestRate;

    @FindBy(id = "loanterm")
    WebElement txt_LoanTenure;

    @FindBy(id = "loanfees")
    WebElement txt_FeesAndCharge;

    @FindBy(id = "loanamountslider")
    WebElement slider_EMICalcLoanAmount;

    @FindBy(id = "loaninterestslider")
    WebElement slider_InterestRate;

    @FindBy(id = "loantermslider")
    WebElement slider_LoanTenure;

    @FindBy(id = "loanfeesslider")
    WebElement slider_FeesAndCharge;

    @FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[@class = 'btn btn-secondary']")
    WebElement btn_Month;

    @FindBy(xpath = "//div[@id = 'loantermsteps']/span/span")
    List<WebElement> displayTxt_preClickSliderValues;

    @FindBy(xpath = "//div[@id = 'loantermsteps']/span/span")
    List<WebElement> displayTxt_postClickSliderValues;

    @FindBy(id = "menu-item-dropdown-2696")
    WebElement dropdown_loanCalculatorDropDown;

    @FindBy(xpath = "//li[@id='menu-item-2423']/a")
    WebElement btn_loanCalculatorOption;

        List<WebElement> txtElements = new ArrayList<>();
        List<WebElement> sliderElements = new ArrayList<>();

        public void initializeTxtElementList() {
            txtElements.add(txt_EMICalcLoanAmount);
            txtElements.add(txt_InterestRate);
            txtElements.add(txt_LoanTenure);
            txtElements.add(txt_FeesAndCharge);
        }

    public void initializeSliderElementList() {
        sliderElements.add(slider_EMICalcLoanAmount);
        sliderElements.add(slider_InterestRate);
        sliderElements.add(slider_LoanTenure);
        sliderElements.add(slider_FeesAndCharge);
    }

    public boolean validateTxtElement(List<WebElement> elements) {
        boolean allValid = true;

        for (WebElement element : elements) {
            if (element == null) {
                String elementName = element.getText();
                System.out.println(elementName+" Element does not exist.");
                allValid = false;
            } else {
                boolean result = element.isDisplayed() && element.isEnabled();
                if (!result) {
                    allValid = false;
                }
            }
        }
        return allValid;
    }

    public boolean validateSliderElement(List<WebElement> elements) {
        boolean allValid = true;

        for (WebElement element : elements) {
            if (element == null) {
                String elementName = element.getText();
                System.out.println(elementName+" Element does not exist.");
                allValid = false;
            } else {
                boolean result = element.isDisplayed() && element.isEnabled();
                if (!result) {
                    allValid = false;
                }
            }
        }
        return allValid;
    }

    public boolean validateEmiTxtElements() {
            return validateTxtElement(txtElements);
    }

    public boolean validateEmiSliderElements() {
        return validateSliderElement(sliderElements);
    }


    public boolean compareChangeInSlider() {
        String preVal = displayTxt_preClickSliderValues.get(1).getText();
        btn_Month.click();
        CommonUtil.sureWait(2);
        String postVal  = displayTxt_postClickSliderValues.get(1).getText();
        if (preVal.equalsIgnoreCase(postVal)) {
            return false;
        }
        return true;
    }
}
