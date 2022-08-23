package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BasicDataBaseOperations {

    public static String dbURL = "jdbc:mysql://localhost:3306/";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            Statement stmt = con.createStatement();

            query = "CREATE database SampleDB";

            boolean result = stmt.execute(query);

            System.out.println("Query executed successfully! Result is: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
