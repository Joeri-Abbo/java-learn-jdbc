package com.skillsoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoredProcedureExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/UniversityDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try {
            Connection con = DriverManager.getConnection(dbURL, username, password);
            CallableStatement cs = con.prepareCall("{call CreateTables(?, ?, ?, ?)}");

            cs.setInt(1, 102);
            cs.setString(2, "Claudia Sand");
            cs.setInt(3, 10015);
            cs.setString(4, "Claudia.Sand@hotmail.com");

            cs.execute();

            cs.setInt(1, 103);
            cs.setString(2, "Harriet Lipsey");
            cs.setInt(3, 10015);
            cs.setString(4, "harriet.lipsey@gmail.com");

            cs.execute();

            cs.close();

            System.out.println("Stored procedure called successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
