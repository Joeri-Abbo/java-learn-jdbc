package com.skillsoft.jdbc;

import java.io.IOException;
import java.sql.*;

import java.sql.SQLException;

public class DBTransactions {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
        String query = null;
        Connection con = null;
        Statement stmt = null;
        Savepoint savepoint = null;

        try {
            con = DriverManager.getConnection(dbURL, username, password);
            con.setAutoCommit(false);

            stmt = con.createStatement();

            query = "INSERT INTO Products values(104, 'Keyboard',12)";
            stmt.executeUpdate(query);

            savepoint = con.setSavepoint("OneRow");
            query = "INSERT INTO Products values(105, 'USB CABLE',3)";
            stmt.executeUpdate(query);

            query = "INSERT INTO Products values(106, 'VGA CABLE',3)";
            stmt.executeUpdate(query);

            query = "INSERT INTO Products values(103, 'LCD Monitor',159)";
            stmt.executeUpdate(query);

            System.out.println("Rows successfully added!");
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            if (savepoint != null) {
                System.out.println("Errors detected. Rolling back to savepoint...");
                con.rollback(savepoint);
                con.commit();

            } else {
                System.out.println("Errors detected. Rolling back...");
                con.rollback();
            }
        } finally {
            con.close();
        }
    }
}
