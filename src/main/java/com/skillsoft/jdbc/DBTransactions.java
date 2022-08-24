package com.skillsoft.jdbc;

import java.io.IOException;
import java.sql.*;

import java.sql.SQLException;
import java.util.Arrays;

public class DBTransactions {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
        String query = null;
        Connection con = null;
        Statement stmt = null;

        try {
            con = DriverManager.getConnection(dbURL, username, password);

            stmt = con.createStatement();

            query = "INSERT INTO Products values(103, 'HDMI CABLE',5)";
            stmt.executeUpdate(query);

            query = "INSERT INTO Products values(102, 'Mouse',15)";
            stmt.executeUpdate(query);

            System.out.println("Rows successfully added!");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
    }
}
