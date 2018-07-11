/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cuongvm
 */
public class NewClass {
    final static Logger log = LoggerFactory.getLogger("logback");
    
    public void printLog(){
        log.info("Info logger");
        log.debug("debug logger");
        log.error("Error logger");
        System.out.println("End!");
    }
}
