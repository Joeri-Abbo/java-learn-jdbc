package com.skillsoft.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;
import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.DriverManager;

public class CachedRowSetExamples {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    private static void displayProductData(String label, RowSet result) throws SQLException {
        int id = result.getInt("product_id");
        String name = result.getString("product_name");
        Double price = result.getDouble("price");
        String prodData = "%s: %d | %s | %.2f \n\n";
        System.out.format(prodData, label, id, name, price);
    }

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            con.setAutoCommit(false);

            CachedRowSet cachedRs = RowSetProvider.newFactory().createCachedRowSet();
            cachedRs.setCommand("SELECT * FROM Products");
            cachedRs.execute(con);

//            while (cachedRs.next()) {
//                if (cachedRs.getString("product_name").endsWith("Cable")) {
//
//                    displayProductData("Deleting: ", cachedRs);
//                    cachedRs.deleteRow();
//                }
//            }

            System.out.println("-----Inserting Rows-----\n");

            int cableCount = 0;
            int prodCount = 0;

            while (cachedRs.next()) {
                prodCount = Math.max(cachedRs.getInt("product_id"), prodCount);

                if (cachedRs.getString("product_name").endsWith("Cable")) {
                    cableCount++;
                }
            }

            if (cableCount == 0) {
                System.out.println("There are no cables in store! Adding one...");
                Thread.sleep(60000);
                cachedRs.moveToInsertRow();
                cachedRs.updateInt("product_id", ++prodCount);
                cachedRs.updateString("product_name", "HDMI Cable");
                cachedRs.updateDouble("price", 5);

                cachedRs.insertRow();
                cachedRs.moveToCurrentRow();

                displayProductData("added: ", cachedRs);

            }

            cachedRs.acceptChanges();
            cachedRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
