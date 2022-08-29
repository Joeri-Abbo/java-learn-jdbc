package com.skillsoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcedureExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/UniversityDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try {
            Connection con = DriverManager.getConnection(dbURL, username, password);

            Statement stmt = con.createStatement();

            String queryDrop = "DROP PROCEDURE IF EXISTS Select_Student";
            String querySelect = """
                    CREATE PROCEDURE Select_Student
                    (IN stud_id INT, OUT student_name VARCHAR(255), OUT sdept_id INT)
                    BEGIN
                    SELECT s.stud_name, sd.dept_id INTO student_name, sdept_id 
                    FROM Student s, StudentDepartment sd
                    WHERE s.stud_id = student_id
                    AND sd.stud_id = student_id;
                    END 
                    """;

            stmt.execute(queryDrop);
            stmt.execute(querySelect);
            stmt.close();
            System.out.println("Stored procedure created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
