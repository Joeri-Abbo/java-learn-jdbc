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

            String query = "INSERT INTO PuppyInfo (puppy_id, puppy_breed,puppy_photo) values(?,?,?)";
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1,100);
            pstmt.setString(2,"American Eskimo");
            FileInputStream input = new FileInputStream("/Users/joeriabbo/Sites/java/learn-jdbc/pets/american_eskimo.jpeg");

            pstmt.setBlob(3, input);
            pstmt.executeUpdate();
            System.out.println("The image upload was a success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
