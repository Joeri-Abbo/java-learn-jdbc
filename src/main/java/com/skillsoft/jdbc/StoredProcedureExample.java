package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcedureExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/UniversityDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            Statement stmt = con.createStatement();

            String queryDrop = "DROP PROCEDURE IF EXISTS Update_Student";

            String queryUpdate = """
                    CREATE PROCEDURE update_student
                    (IN student_id INT, INOUT student_email VARCHAR(255))
                    BEGIN
                    DECLARE temp_email VARCHAR(255);
                                        
                    SELECT email INTO temp_email
                    FROM Student
                    WHERE stud_id = student_id;
                                        
                    UPDATE Student SET email = student_email
                    WHERE stud_id = student_id;
                    SET student_email = temp_email;
                                        
                    END
                                        
                    """;

            stmt.execute(queryDrop);
            stmt.execute(queryUpdate);

            stmt.close();

            System.out.println("Stored procedure created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
