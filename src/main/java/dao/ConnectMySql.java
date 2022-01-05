package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;

public class ConnectMySql {
    public static Connection getConncetion(){
        String jdbcURL = "jdbc:mysql://localhost:3306/QLBH";
        String jdbcUsername = "root";
        String jdbcPassword = "12345678";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
