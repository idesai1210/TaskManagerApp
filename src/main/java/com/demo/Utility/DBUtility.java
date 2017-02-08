package com.demo.Utility;

/**
 * Created by Ishan on 2/6/17.
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                //Properties prop = new Properties();
                //InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("/config.properties");
                //prop.load(inputStream);
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/demo";
                String user = "root";
                String password = "root";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}