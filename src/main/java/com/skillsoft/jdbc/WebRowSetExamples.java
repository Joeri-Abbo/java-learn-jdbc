package com.skillsoft.jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetExamples {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws IOException, SQLException {
        WebRowSet webrs = null;
        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            con.setAutoCommit(false);

            webrs = RowSetProvider.newFactory().createWebRowSet();

            webrs.setCommand("SELECT * FROM Products");
            webrs.execute(con);

            System.out.println("Size of the WebRowSet is: " + webrs.size());

            String path = "ProductsXML.xml";

            File myFile = new File(path);
            FileWriter writer = new FileWriter(myFile);

            System.out.println("Writing the Products table to an XML File:" + myFile.getAbsolutePath());
            webrs.writeXml(writer);

            writer.flush();
            writer.close();
            webrs.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
