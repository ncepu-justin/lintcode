package com.company.reflect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Justin
 * 2019/5/29  21:20
 */
public class SingletonConnection {
    private enum EnumSingleton {

        connectionFactory;
        private Connection conn;

        EnumSingleton() {
            try {
                String url = "jdbc:mysql://localhost:3306/mydb";
                String name = "root";
                String password = "";
                String Driver = "com.mysql.jdbc.Driver";

                Class.forName(Driver);
                conn = DriverManager.getConnection(url, name, password);
            } catch (SQLException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private Connection getConn() {
            return conn;
        }

    }

    public static Connection getConn() {
        return EnumSingleton.connectionFactory.getConn();
    }

}
