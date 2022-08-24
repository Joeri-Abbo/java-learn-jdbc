package com.skillsoft.jdbc;

import java.sql.*;

import java.sql.SQLException;
import java.util.Arrays;

public class BatchUpdates {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        Statement stmt = null;

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            stmt = con.createStatement();

            stmt.addBatch("INSERT INTO Products values(101, 'Mother Board', 79)");
            stmt.addBatch("INSERT INTO Products values(102, 'Mouse', 15)");
            stmt.addBatch("INSERT INTO Products values(103, 'HDMI Cable', 5)");
            stmt.addBatch("INSERT INTO Products values(104, 'Keyboard', 12)");
            stmt.addBatch("INSERT INTO Products values(105, 'USB cable', 3)");
            stmt.addBatch("INSERT INTO Products values(106, 'VGA Cable', 3)");

            int[] updateCounts = stmt.executeBatch();

            System.out.println(Arrays.toString(updateCounts));
        } catch (BatchUpdateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
