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
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zeros.vnpay.model.Order;

/**
 *
 * @author cuongvm
 */
public class GetData {

    private static Logger log = LoggerFactory.getLogger("logback");
    private static String sql;
    private static Connection con;
    private static PreparedStatement ps;
    private static DataSource dataSource = (DataSource) new ClassPathXmlApplicationContext("dataSource.xml").getBean("dataSource");
    

    public static Order getOrderByID(int id) throws SQLException, ClassNotFoundException {
        Order o = new Order();
        try {
            sql = "Select * from `order`.order where idorder = ?";
            log.info("Start get Connection ...");
            con = dataSource.getConnection();
            log.info("getConnection succesfull!!!");
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                o = new Order(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getTime(6),
                        rs.getString(5));
            }
            log.info("Get Order succesfull!!!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return o;
    }

    public static void insert(Order o) throws SQLException, ClassNotFoundException {
        log.info("Start get Connection ...");
        con = dataSource.getConnection();
        log.info("getConnection succesfull!!!");
        try {
            sql = "insert into `order`.order (totalPrice, content, dateCreate, property, timeCreate) values( ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setDouble(1, o.getTotalPrice());
            ps.setString(2, o.getContent());
            ps.setDate(3, o.getDate());
            ps.setString(4, o.getProperty());
            ps.setTime(5, o.getTime());
            ps.executeUpdate();
            log.info("insert succesfull!!!");

        } catch (SQLException ex) {
            log.error(GetData.class.getName());
        }
    }

    public static List<Order> getListOrder(int page, int size) throws SQLException, ClassNotFoundException {
        List<Order> list = new ArrayList<>();
        con = dataSource.getConnection();
        log.info("getConnection succesfull!!!");
        try {
            sql = ("SELECT * FROM `order`.`order` order by idorder DESC limit ?,?");
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
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
            log.info("End get Orders!");
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        log.info("Return List Order");
        return list;
    }

    public static int countRecord() throws SQLException{
        sql = "select count(idorder) from `order`.order";
        int count = 0;
        con = dataSource.getConnection();
        ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            count = rs.getInt(1);
        return count;
    }
}
