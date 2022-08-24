package com.skillsoft.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import java.sql.SQLException;

public class DBTransactions {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
        String query = null;
        Connection con = null;
        Statement stmt = null;

        ArrayList<Savepoint> listOfSavepoints = new ArrayList<Savepoint>();
        try {
            con = DriverManager.getConnection(dbURL, username, password);
            con.setAutoCommit(false);

            stmt = con.createStatement();

            query = "INSERT INTO Products values(105, 'USB CABLE',3)";
            stmt.executeUpdate(query);
            listOfSavepoints.add(con.setSavepoint("OneRow"));

            query = "INSERT INTO Products values(106, 'VGA CABLE',3)";
            stmt.executeUpdate(query);

            query = "INSERT INTO Products values(107, 'LCD Monitor',159)";
            stmt.executeUpdate(query);
            listOfSavepoints.add(con.setSavepoint("threeRows"));

            query = "INSERT INTO Products values(108, 'Curved LCD Monitor',199)";
            stmt.executeUpdate(query);

            query = "INSERT INTO Products values(103, 'Speaker',79)";
            stmt.executeUpdate(query);
            listOfSavepoints.add(con.setSavepoint("FiveRows"));

            System.out.println("Rows successfully added!");
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            if (listOfSavepoints.size() != 0) {
                Savepoint sp = listOfSavepoints.get(listOfSavepoints.size() - 1);
                con.rollback(sp);
                con.commit();
                System.out.println("Errors detected. Rolling back to savepoint: " + sp.getSavepointName());

            } else {
                System.out.println("Errors detected. Rolling back...");
                con.rollback();
            }
        } finally {
            con.close();
        }
    }
}
