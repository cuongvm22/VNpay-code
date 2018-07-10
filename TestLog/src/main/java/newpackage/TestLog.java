/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cuongvm
 */
public class TestLog {

    static final Logger log = LoggerFactory.getLogger("logback");

    public static void main(String[] args) {
        new jdev.model.NewClass().printLog();

    }
}
