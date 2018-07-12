

import com.google.maps.errors.ApiException;
import controller.WriteToFile;
import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cuongvm
 */
public class App {

    public static void main(String[] args) throws  InterruptedException, IOException, ApiException {
        Scanner s = new Scanner(System.in);
        System.out.println("InCol");
        int inCol = Integer.parseInt(s.nextLine());
        System.out.println("outCol");
        int outCol = Integer.parseInt(s.nextLine());
        
        new WriteToFile("data/DS Diemketnoi.xlsx", 0).Write(inCol, outCol);
//        System.out.println("run");
//        try {
//            log.warn("Test logback!!!");
//            System.err.println("Error!");
//            log.debug("TestLog");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
