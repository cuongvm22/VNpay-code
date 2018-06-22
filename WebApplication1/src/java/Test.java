
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuongvm
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
                Connection con;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/order", "root", "admin");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select count(idorder) from `order`.`order`;");
                
                 int total = 0;
                 while (rs.next()){
                 total= Integer.parseInt(rs.getString(1));
                 }
                con.close();
                System.out.println(total);
    }
}
