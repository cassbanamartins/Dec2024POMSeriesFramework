package com.qa.opencart.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
    private static Workbook book;
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {
        Object data[][] = null;
        try {
            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);

            data = new Object[ExcelUtil.sheet.getLastRowNum()][ExcelUtil.sheet.getRow(0).getLastCellNum()];

            //ExcelUtil.sheet.getLastRowNum() - Total Number of Rows
            // ExcelUtil.sheet.getRow(0) - Get the 0th row
            // getLastCellNum() - give me the last cell num
            // object array [][] data will be created
            for (int i = 0; i < ExcelUtil.sheet.getLastRowNum(); i++) {
                for (int j = 0; j < ExcelUtil.sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] = ExcelUtil.sheet.getRow(i + 1).getCell(j).toString();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }
}