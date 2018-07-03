/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author cuongvm
 */
public class WorkBookData extends XSSFWorkbook{
    public XSSFWorkbook getWorkBook(String pathFile) throws FileNotFoundException, IOException {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(pathFile)));
        return wb;
    }
    
}
