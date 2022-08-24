package com.skillsoft.jdbc;

import java.sql.*;

public class ResultSetFeatures {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";


    private static void displayEmpData(String label, ResultSet result) throws SQLException {
        int id = result.getInt("emp_id");
        String name = result.getString("emp_name");
        String designation = result.getString("designation");
        Double salary = result.getDouble("salary");

        String empData = "%s: %d | %s | %s | %.2f \n";
        System.out.format(empData, label, id, name, designation, salary);
    }

    public static void main(String[] args) throws SQLException {

        String query = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            query = "SELECT * FROM employee_data";

            ResultSet rs = stmt.executeQuery(query);

            System.out.println();
            rs.next();
            displayEmpData("next()", rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
