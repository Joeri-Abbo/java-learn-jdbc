package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicDataBaseOperations {

    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            Statement stmt = con.createStatement();

            query = "CREATE TABLE users (first_name VARCHAR(50), last_name VARCHAR(50), email VARCHAR(50), phone_number VARCHAR(50))";
            stmt.execute(query);

            System.out.println("Query executed successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
