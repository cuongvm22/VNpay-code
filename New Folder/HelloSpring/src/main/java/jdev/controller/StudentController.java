/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdev.controller;

import java.util.ArrayList;
import jdev.model.Student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cuongvm
 */
@Controller
@RequestMapping("/")
public class StudentController {
    
    @RequestMapping("/sinhvien")
    public String index(ModelMap modelMap) {
        ArrayList<Student> listSV = new ArrayList();
        listSV.add(new Student(1, "Đoàn Phương Linh"));
        listSV.add(new Student(2, "Lưu Bị"));
        listSV.add(new Student(3, "Tào Tháo"));
        listSV.add(new Student(4, "Phú IT"));
        modelMap.addAttribute("world", "Hello");
        modelMap.addAttribute("listSV", listSV);
        return "/../index";
    }

}
