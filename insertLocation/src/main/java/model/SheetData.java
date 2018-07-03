/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author cuongvm
 */
public class SheetData extends XSSFSheet{
    public XSSFSheet getSheetData(String pathFile, String sheetName) throws IOException{
        return new WorkBookData().getWorkBook(pathFile).getSheet(sheetName);
    }
     public XSSFSheet getSheetData(String pathFile, int index) throws IOException{
        return new WorkBookData().getWorkBook(pathFile).getSheetAt(index);
    }
}
