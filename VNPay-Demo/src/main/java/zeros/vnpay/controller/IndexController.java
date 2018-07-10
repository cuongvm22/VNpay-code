package zeros.vnpay.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zeros.vnpay.getdata.GetData;
import zeros.vnpay.getdata.GetPaginationNumber;

/**
 *
 * @author cuongvm
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(@RequestParam("pages") int page, ModelMap m) throws SQLException, ClassNotFoundException {
        List<Integer> pag = GetPaginationNumber.paginationNumber(page, 20);
        m.addAttribute("paginationNumber", pag);
        if (page == 1) {
            m.addAttribute("back", 1);
            m.addAttribute("next", page + 1);
        } else if (page == pag.get(pag.size() - 1)) {
            m.addAttribute("back", page - 1);
            m.addAttribute("next", pag.get(pag.size() - 1));
        } else {
            m.addAttribute("back", page - 1);
            m.addAttribute("next", page + 1);
        }
        m.addAttribute("listOrder", GetData.getListOrder(page, 20));
        return "index";
    }

}
