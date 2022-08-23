package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCIntro {
    public static void main(String[] args) throws ClassNotFoundException {
        Connection con = null;

        try {
            con = MySQLDBUtil.getConnection();

            if (con != null) {
                System.out.println("The connection has been successfully established!");
            }
        } catch (SQLException e) {
            System.out.println("A connection error has occurred:");
            e.printStackTrace();
        }
    }
}