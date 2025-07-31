package com.cts.hackathon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalLoan extends BasePage{


    public PersonalLoan(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='emicalculatorinnerform']/div[1]/label")
    WebElement txt_personalLoan;

    public String verifyPersonalLoanPage(){
        return txt_personalLoan.getText();
    }

}
