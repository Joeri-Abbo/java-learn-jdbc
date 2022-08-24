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
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            query = "SELECT * FROM employee_data";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            displayEmpData("next()", rs);

            Thread.sleep(120000);
            rs.last();
            rs.refreshRow();
            displayEmpData("last()", rs);

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
