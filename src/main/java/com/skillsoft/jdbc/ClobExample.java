package com.skillsoft.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;

public class ClobExample {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws FileNotFoundException {
        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM PuppyInfo";

            ResultSet rs = stmt.executeQuery(query);

            HashMap<String, String> dogDescFileMap = new HashMap<String, String>();

            dogDescFileMap.put("American Eskimo", "american_eskimo.txt");
            dogDescFileMap.put("Pug", "pug.txt");
            dogDescFileMap.put("Labrador", "labrador.txt");

            while (rs.next()) {
                String dogBreed = rs.getString("puppy_breed");

                String descFileName = dogDescFileMap.get(dogBreed);


                Clob clob = rs.getClob("puppy_desc");

                Reader r = clob.getCharacterStream();

                FileWriter writer = new FileWriter(descFileName);

                int i;
                while ((i = r.read()) != -1) {
                    writer.write(i);
                }
                writer.close();
                r.close();
                System.out.println("Saved description in: " + descFileName);
            }
            rs.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
