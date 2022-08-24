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
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            con.setAutoCommit(false);
            query = "SELECT * FROM employee_data";

            ResultSet rs = stmt.executeQuery(query);

            int javaDevCount = 0;
            int empCount = 0;
            while (rs.next()) {
                empCount = Math.max(rs.getInt("emp_id"), empCount);

                if (rs.getString("designation").equalsIgnoreCase("Java Developer")) {
                    javaDevCount++;
                }
            }

            if (javaDevCount == 0) {
                rs.moveToInsertRow();

                rs.updateInt(1, ++empCount);
                rs.updateString(2, "Alan");
                rs.updateString(3, "Java Developer");
                rs.updateString(4, "95000");

                rs.insertRow();
                rs.last();

                displayEmpData("added:", rs);
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
