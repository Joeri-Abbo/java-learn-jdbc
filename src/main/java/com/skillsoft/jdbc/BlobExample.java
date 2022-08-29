package com.skillsoft.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws FileNotFoundException {
        PreparedStatement pstmt = null;
        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            String query = "UPDATE PuppyInfo SET puppy_photo =? WHERE puppy_Breed = 'Pug'";

            pstmt = con.prepareStatement(query);

            FileInputStream input = new FileInputStream("/Users/joeriabbo/Sites/java/learn-jdbc/pets/pug.jpg");

            pstmt.setBlob(1, input);
            pstmt.executeUpdate();
            System.out.println("The image upload was a success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
