/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.vnpay.controller;

import java.sql.SQLException;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zeros.vnpay.getdata.GetData;

/**
 *
 * @author cuongvm
 */
@Controller
public class DetailController {

    @RequestMapping("/detail")
    public String detail(@RequestParam("id") int id, ModelMap m) throws SQLException, ClassNotFoundException {
        m.addAttribute("order", GetData.getOrderByID(id));
        return "detail";
    }

}
