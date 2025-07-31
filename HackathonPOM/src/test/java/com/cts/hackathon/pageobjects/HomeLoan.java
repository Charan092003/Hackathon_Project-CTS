package com.cts.hackathon.pageobjects;

import com.cts.miniproject.frameworkutils.ExcelWrite;
import com.cts.miniproject.seleniumutils.KeyBoardActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomeLoan extends BasePage {


    public HomeLoan(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "loanamount")
    WebElement txt_homeLoanAmount;

    @FindBy(id = "loaninterest")
    WebElement txt_interestRate;

    @FindBy(id = "loanterm")
    WebElement txt_loanTenure;

    @FindBy(xpath = "//input[@id='loanyears']/..")
    WebElement btn_year;

    @FindBy(xpath = "//div[@id='emipaymenttable']/table/tbody/tr[@class='row no-margin yearlypaymentdetails']")
    List<WebElement> displaytxt_yearTableRows;

    @FindBy(xpath = "//div[@id='emicalculatorinnerform']/div[1]/label")
    WebElement txt_homeLoan;

    @FindBy(id="year2025")
    WebElement btn_year2025;

    public String verifyHomeLoanPage(){
        return txt_homeLoan.getText();
    }

    KeyBoardActions keyBoardActions = new KeyBoardActions();

    public void setHomeLoanAmount(String homeLoanAmount){
        keyBoardActions.clearTextBox(txt_homeLoanAmount);
        txt_homeLoanAmount.sendKeys(homeLoanAmount);
    }

    public void setInterestRate(String interestRate){
        keyBoardActions.clearTextBox(txt_interestRate);
        txt_interestRate.sendKeys(interestRate);
    }

    public void setLoanTenure(String interestRate) {
        keyBoardActions.clearTextBox(txt_loanTenure);
        txt_loanTenure.sendKeys(interestRate);
    }

    public void clickYear() {
        btn_year.click();
    }

    public boolean checkTable(){
        return btn_year2025.isEnabled();
    }

    public int noOfRows(){
        return displaytxt_yearTableRows.size();
    }

    public boolean printYearTableData() {
        try {
            List<List<String>> tableData = new ArrayList<>();

            for (int i = 0; i < displaytxt_yearTableRows.size(); i++) {
                WebElement row = displaytxt_yearTableRows.get(i);
                List<WebElement> cells = row.findElements(By.tagName("td"));
                List<String> rowData = new ArrayList<>();

                for (int j = 0; j < cells.size(); j++) {
                    WebElement cell = cells.get(j);
                    String cellValue = cell.getText().trim();
                    rowData.add(cellValue);
                }

                tableData.add(rowData);
            }

            // Call Excel writer
            ExcelWrite.writeToExcel(tableData, "EMI_Yearly_Table.xlsx");
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }




}
