package com.cts.miniproject.frameworkutils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DataProvider {
    @org.testng.annotations.DataProvider(name="mp")
    public static String[][] getData(Method testMethod){
        ArrayList<String[]> allRowsTestData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("testdata/testdata.xlsx")) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet("Sheet1");
            int rowCount = s.getPhysicalNumberOfRows();
            for (int r = 1; r < rowCount; r++) {
                Row eachRow = s.getRow(r);
                int cellCount = eachRow.getPhysicalNumberOfCells();
                String tcn = eachRow.getCell(2).getStringCellValue().trim().replaceAll(" ", "").toLowerCase();
                String tcnrunStatus = eachRow.getCell(3).getStringCellValue();
                if (tcn.equalsIgnoreCase(testMethod.getName().toLowerCase()) && tcnrunStatus.equalsIgnoreCase("Y")) {
                    ArrayList<String> eachRow_allCellsData = new ArrayList<>();

                    for (int eachCell = 4; eachCell < 4+testMethod.getParameterCount(); eachCell++) {
                        String celldata = eachRow.getCell(eachCell).getStringCellValue();
                        eachRow_allCellsData.add(celldata);
                    }
                    allRowsTestData.add(eachRow_allCellsData.toArray(new String[0]));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return allRowsTestData.toArray(new String[0][0]);
    }
}
