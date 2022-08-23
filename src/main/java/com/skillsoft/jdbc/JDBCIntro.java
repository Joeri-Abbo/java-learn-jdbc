package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCIntro {
    public static void main(String[] args) throws ClassNotFoundException {
        String databaseURL = "jdbc:mysql://localhost:3306";

        Properties connectionProps = new Properties();

        connectionProps.put("user", "mysql");
        connectionProps.put("password", "mysql");

        Connection con = null;

        try {
            con = DriverManager.getConnection(databaseURL, connectionProps);

            if (con != null) {
                System.out.println("The connection has been successfully established!");
            }
        } catch (SQLException e) {
            System.out.println("A connection error has occurred:");
            e.printStackTrace();
        }
    }
}