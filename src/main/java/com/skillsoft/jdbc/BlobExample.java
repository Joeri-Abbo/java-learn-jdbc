package com.skillsoft.jdbc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;

public class BlobExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream outputFile = null;
        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            CachedRowSet cachedRs = RowSetProvider.newFactory().createCachedRowSet();

            cachedRs.setCommand("SELECT * FROM PuppyInfo");
            cachedRs.execute(con);
            cachedRs.beforeFirst();

            while (cachedRs.next()) {
                byte byteArray[] = cachedRs.getBytes("puppy_photo");

                outputFile = new FileOutputStream("pets/"+cachedRs.getInt("puppy_id") + ".jpg");
                outputFile.write(byteArray);

                System.out.println("Image of " + cachedRs.getString("puppy_breed") + " saved to pets/" + cachedRs.getInt("puppy_id") + ".jpg");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
