package com.skillsoft.jdbc;

import java.sql.*;

public class StoredProcedureExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/UniversityDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try {
            Connection con = DriverManager.getConnection(dbURL, username, password);

            CallableStatement cs = con.prepareCall("{call update_student(?,?)}");

            cs.setInt(1, 102);

            cs.setString(2, "Claudia.Sand@loonycorn.com");
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();

            System.out.println("The old email ID = " +cs.getString(2));
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
