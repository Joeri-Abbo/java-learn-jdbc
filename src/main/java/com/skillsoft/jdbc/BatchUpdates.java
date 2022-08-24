package com.skillsoft.jdbc;

import java.sql.*;

import java.sql.SQLException;
import java.util.Arrays;

public class BatchUpdates {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {
        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Products values(?,?,?)");

            pstmt.setInt(1, 101);
            pstmt.setString(2, "Mother Board");
            pstmt.setDouble(3, 79);
            pstmt.addBatch();

            pstmt.setInt(1, 102);
            pstmt.setString(2, "Mouse");
            pstmt.setDouble(3, 15);
            pstmt.addBatch();

            pstmt.setInt(1, 103);
            pstmt.setString(2, "HDMI Cable");
            pstmt.setDouble(3, 5);
            pstmt.addBatch();

            pstmt.setInt(1, 104);
            pstmt.setString(2, "Keyboard");
            pstmt.setDouble(3, 12);
            pstmt.addBatch();

            pstmt.setInt(1, 105);
            pstmt.setString(2, "USB cable");
            pstmt.setDouble(3, 3);
            pstmt.addBatch();

            pstmt.setInt(1, 106);
            pstmt.setString(2, "VGA cable");
            pstmt.setDouble(3, 3);
            pstmt.addBatch();

            int[] updateCounts = pstmt.executeBatch();

            System.out.println(Arrays.toString(updateCounts));

            con.commit();
        } catch (BatchUpdateException e) {
            System.out.println("----BatchUpdateExecution----");
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Vendor: " + e.getErrorCode());

            System.out.println(Arrays.toString(e.getUpdateCounts()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
