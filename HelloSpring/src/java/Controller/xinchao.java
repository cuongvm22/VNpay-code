/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author cuongvm
 */
@Controller
@RequestMapping(value="/hello")
public class xinchao {
    
    @RequestMapping(value="/xinchao", method = RequestMethod.GET)
    public String sayHello(ModelMap mm){
        mm.put("hello", "Hello every one!");
        mm.put("name", "My name is Cuong");
        
        return "helloWorld";
    }
}
