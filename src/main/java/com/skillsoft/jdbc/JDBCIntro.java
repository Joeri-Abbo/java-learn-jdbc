package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCIntro {
    public static void main(String[] args) throws ClassNotFoundException {
        String databaseURL = "jdbc:mysql://localhost:3306";

        String user = "mysql";
        String password = "mysql";
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(databaseURL, user, password);

            if (con != null) {
                System.out.println("The connection has been successfully established!");
            }
        } catch (SQLException e) {
            System.out.println("A connection error has occurred:");
            e.printStackTrace();
        }
    }
}