/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.WorkBookData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cuongvm
 */
public class WriteToFile {

    private static Logger log = LoggerFactory.getLogger("logback");
    private XSSFSheet sheet;
    private XSSFWorkbook wb;
    private List<String> apiKey;
    private String pathFile;

    public WriteToFile(String pathFile, String sheetName) throws FileNotFoundException, IOException {
        try {
            log.info("Get WorkBook...");
            this.wb = new WorkBookData().getWorkBook(pathFile);
            log.info("Get sheet...");
            this.sheet = wb.getSheet(sheetName);
            log.info("Get succesfull!");
            this.pathFile = pathFile;
            
            
            log.info("Getting ApiKey from apikey.text");
            apiKey = new GetApiKeys().getApiKeys("data/apikey.txt");
            log.info("Get ApiKey from apikey.text sucessfully!");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public WriteToFile(String pathFile, int index) throws FileNotFoundException, IOException {
        this.wb = new WorkBookData().getWorkBook(pathFile);
        this.sheet = wb.getSheetAt(index);
        try {
            log.info("Getting ApiKey from apikey.text");
            apiKey = new GetApiKeys().getApiKeys("data/apikey.txt");
            log.info("Get ApiKey from apikey.text sucessfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void Write(int inCol, int outCol) throws ApiException, InterruptedException, IOException {
        int keyIndex = 0;
        log.info("Start to update location ...");
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            try {
                LatLng result = new GetLocation().GetLocation(apiKey.get(keyIndex), sheet.getRow(i).getCell(inCol).getStringCellValue());
                sheet.getRow(i).getCell(outCol).setCellValue(result.lat);
                sheet.getRow(i).getCell(outCol + 1).setCellValue(result.lng);
                if (i % 2000 == 1 && i > 2000 && keyIndex < apiKey.size()) {
                    log.info("Write from " + (keyIndex * 2000 + 1) + " to " + ((keyIndex + 1) * 2000 + 1));
                    wb.write(new FileOutputStream(new File(pathFile)));
                    log.info("Write sucessfully!!!");
                    keyIndex++;
                }
            } catch (Exception e) {
                log.error("Line " + i + ", Error: " + e.getMessage());
                continue;
            }
        }
        log.info("Update succesfully!!!");

    }

    
}
