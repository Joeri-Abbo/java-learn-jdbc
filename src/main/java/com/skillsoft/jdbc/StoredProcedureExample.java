package com.skillsoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class StoredProcedureExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/UniversityDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {


            CallableStatement cs = con.prepareCall("{call SelectStudent(?, ?, ?)}");

            cs.setInt(1, 102);

            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();


            System.out.println("Student Name = " + cs.getString(2));
            System.out.println("Student Department_ID = " + cs.getInt(3));

            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
