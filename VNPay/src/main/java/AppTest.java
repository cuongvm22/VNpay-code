
import java.sql.SQLException;
import java.util.List;
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
public class AppTest {
    public static void main(String[] args) throws SQLException {
        Order o10 = GetData.getOrderByID(10);
        System.out.println(o10.toString());
        List<Order> list = GetData.getListOrder(1, 20);
        for (Order o : list) {
            System.out.println(o.getId());
        }
        
    }
}
