/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeros.vnpay.getdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zeros.vnpay.model.Order;

/**
 *
 * @author cuongvm
 */
public class GetData {

    private static String sql;
    private static ResultSet rs = null;
    private static Connection con = null;
    private static PreparedStatement ps;

    public static Order getOrderByID(int id) throws SQLException {
        sql = "Select * from `order`.order where idorder = ?";
        con = ConnectionDB.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Order(
                rs.getInt(1),
                rs.getDouble(2),
                rs.getString(3),
                rs.getDate(4),
                rs.getTime(6),
                rs.getString(5));
    }

    public static void insert(Order o) throws SQLException {
        con = ConnectionDB.getConnection();
        try {
            sql = "insert into `order`.order (totalPrice, content, dateCreate, property, timeCreate) values( ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setDouble(1, o.getTotalPrice());
            ps.setString(2, o.getContent());
            ps.setDate(3, o.getDate());
            ps.setString(4, o.getProperty());
            ps.setTime(5, o.getTime());
            ps.executeUpdate();
            System.out.println("Insert sucessfull!!!");

        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Order> getListOrder(int page, int size) throws SQLException {
        List<Order> list = new ArrayList<>();
        con = ConnectionDB.getConnection();
        try {
            sql = ("SELECT * FROM `order`.`order` order by idorder DESC limit ?,?");
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * size);
            ps.setInt(2, size);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(
                        new Order(
                                rs.getInt(1),
                                rs.getDouble(2),
                                rs.getString(3),
                                rs.getDate(4),
                                rs.getTime(6),
                                rs.getString(5)
                        )
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(GetData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
