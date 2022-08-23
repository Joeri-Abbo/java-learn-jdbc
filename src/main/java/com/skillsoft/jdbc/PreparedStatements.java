package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class PreparedStatements {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            query = "SELECT  emp_id, emp_name, designation FROM employee_data WHERE emp_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, 103);
            System.out.println("Contents of the table");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("emp_name") + "\t" + rs.getString("designation") + "\n");
            }

            System.out.println("Query executed successfully! Result is: " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
