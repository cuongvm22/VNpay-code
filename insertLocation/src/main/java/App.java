

import com.google.maps.errors.ApiException;
import controller.WriteToFile;
import java.io.IOException;
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
         new WriteToFile("data/DS Diemketnoi.xlsx", 0).Write();
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
