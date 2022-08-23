package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BasicDataBaseOperations {

    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            Statement stmt = con.createStatement();

            query = "SELECT `users`.first_name, `users`.last_name FROM users";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.print(rs.getString("first_name"));
                System.out.print("\t" + rs.getString("last_name"));
                System.out.println();
            }

            System.out.println("Query executed successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
