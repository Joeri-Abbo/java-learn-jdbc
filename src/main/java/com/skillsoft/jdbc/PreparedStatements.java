package com.skillsoft.jdbc;

import java.sql.*;

public class PreparedStatements {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            Statement stmt = con.createStatement();

            query = "INSERT INTO employee_data(emp_id,emp_name,designation,salary) " + "values (101,'Alan', 'Java Developer', 87000)," + " (102,'Claudia', 'Senior Software Engineer', 102000)";
            int updatedRows = stmt.executeUpdate(query);
            System.out.println("Query executed successfully! Rows updated: " + updatedRows);

            System.out.println("Contents of the table");

            query = "SELECT * FROM employee_data order by emp_name";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.print(rs.getString("emp_name") + "\t" + rs.getString("designation") + "\n");
            }

            System.out.println("Query executed successfully! Result is: " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
