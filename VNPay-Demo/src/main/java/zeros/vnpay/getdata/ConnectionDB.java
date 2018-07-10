package zeros.vnpay.getdata;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cuongvm
 */
public class ConnectionDB {

    public static Connection getConnection(String hostName, String dbName, String user, String pass) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection con = DriverManager.getConnection(url, user, pass);
        return con;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/order", "root", "admin");
    }

}
