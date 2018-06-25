/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.mavenproject2;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author cuongvm
 */
public class NewClass {

    public static void main(String[] args) throws ApiException, InterruptedException, IOException {
        List<String> apiKey = new ArrayList<String>();
        Scanner s = new Scanner(new File("data/apikey.txt"));
        while (s.hasNext()) {
            apiKey.add(s.nextLine());
        }

        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDoYsgT7GmC8puHWM19rGV9Xam-3zooC1w").build();
        GeocodingResult[] results = null;
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("data/DS Diemketnoi.xlsx")));
        int total = workbook.getSheetAt(0).getPhysicalNumberOfRows();

        for (int i = 1; i < total / 2000 + 1; i++) {
            int end = (((i+1)*2000)>total)? total:((i+1)*2000);
            insertLocation((i * 2000 + 1),end ,apiKey.get(i) , "data/DS Diemketnoi.xlsx");
        }
    }

    public static void insertLocation(int start, int end, String apiKey, String pathFile) throws ApiException, InterruptedException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(pathFile)));
        XSSFSheet sheet = workbook.getSheetAt(0);

        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        GeocodingResult[] results;
        for (int i = start; i < end; i++) {
            if (sheet.getRow(i).getCell(4).getStringCellValue() == null) {
                continue;
            }
            results = GeocodingApi.geocode(context, sheet.getRow(i).getCell(4).getStringCellValue()).await();
            if (results.length == 0) {
                continue;
            }
            Cell cell = sheet.getRow(i).getCell(10);
            cell.setCellValue(results[0].geometry.location.lat);
            cell = sheet.getRow(i).getCell(11);
            cell.setCellValue(results[0].geometry.location.lng);
            TimeUnit.SECONDS.sleep(1);
        }
        workbook.write(new FileOutputStream(pathFile));
        
        System.out.printf("Insert succesfull %d record\n", (start + 1)* 2000 );
    }
        //@Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        try {
//            Map data = new HashMap<String, String>();
//
//            GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBs5tL2NKzpKEQ60wTCzyBLDjVfq3_hJ6I").build();
//            GeocodingResult[] results = GeocodingApi.geocode(context, (String) req.getParameter("address")).await();
//            //cover results to json
//            String result = "";
//            for (int i = 0; i < results.length; i++) {
//                if (results.length != 0) {
//                    // result = new GsonBuilder().setPrettyPrinting().create().toJson(results[0]);
//                    result += results[i].formattedAddress + "\n";
//                    req.setAttribute("address", result);
//                    req.setAttribute("lat", results[0].geometry.location.lat);
//                    req.setAttribute("lng", results[0].geometry.location.lng);
//                    //req.setAttribute("json", result);
//
//                } else {
//                    req.setAttribute("address", "Can't find this address!");
//                }
//            }
//
//            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
//            rd.forward(req, resp);
//
//        } catch (ApiException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    }
