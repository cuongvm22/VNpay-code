package zeros.vnpay.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zeros.vnpay.getdata.GetData;
import zeros.vnpay.model.Order;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cuongvm
 */
@Controller
public class CreateOrderController {

    @RequestMapping("/createOrder")
    public String createOrder() {
        return "createOrder";
    }

    @RequestMapping("/insert")
    public String insert(@RequestParam("totalPrice") int totalPrice, @RequestParam("content") String content, @RequestParam("property") String property, @RequestParam("date") Date d, @RequestParam("time") Time t) throws SQLException, ClassNotFoundException {
        try {
            GetData.insert(new Order(totalPrice, content, d, t, property));
        } catch(Exception e){
            return "failed";
        }
        return "completed";
    }
}
