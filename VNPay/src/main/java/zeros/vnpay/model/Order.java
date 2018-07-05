/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.vnpay.model;

import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
/**
 *
 * @author cuongvm
 */
@Getter

public class Order {
    private int id;
    private double totalPrice;
    private String content;
    private Date date;
    private Time time;
    private String property;

    public Order(int id, double totalPrice, String content, Date date, Time time, String property) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.content = content;
        this.date = date;
        this.time = time;
        this.property = property;
    }
    
    
            
}
