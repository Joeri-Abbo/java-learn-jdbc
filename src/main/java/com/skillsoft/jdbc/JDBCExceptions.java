package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExceptions {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {
        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            Statement stmt = con.createStatement();

            System.out.println("Contents of the table");

            ResultSet rs = stmt.executeQuery(null);

            while (rs.next()) {
                System.out.print(rs.getString("emp_id") + "\t" + rs.getString("emp_name") + "\t" + rs.getString("designation") + "\n");
            }

        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Localized Message: " + e.getLocalizedMessage());
            System.out.println("Cause: " + e.getCause());
        }
    }
}
