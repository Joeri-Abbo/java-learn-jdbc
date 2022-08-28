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

            while (webrs.next()){
                if (webrs.getString("product_name").endsWith("Cable")){
                    webrs.deleteRow();
                }

                if (webrs.getString("product_name").contains("Mouse")){
                    webrs.updateDouble("Price", webrs.getDouble("price") + 2);
                    webrs.updateRow();
                }
            }

            webrs.moveToInsertRow();
            webrs.updateInt("product_id", 108);
            webrs.updateString("product_name", "Curved LCD Monitor");
            webrs.updateDouble("price", 199);

            webrs.insertRow();
            webrs.moveToCurrentRow();

            String path = "ProductsXML.xml";

            File myFile = new File(path);
            FileWriter writer = new FileWriter(myFile);

            System.out.println("Writing the Products table to an XML File:" + myFile.getAbsolutePath());
            webrs.writeXml(writer);

            writer.flush();
            writer.close();

            System.out.println("Saving changes to the database...");
            webrs.acceptChanges();

            webrs.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
