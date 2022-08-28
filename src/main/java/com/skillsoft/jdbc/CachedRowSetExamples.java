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
            cachedRs.execute(con);

            System.out.println("-----Updating rows-----");

            while (cachedRs.next()) {
                if (cachedRs.getString("product_name").endsWith("Cable")) {

                    cachedRs.updateDouble("price", cachedRs.getDouble("price") + 1);
                    cachedRs.updateRow();

                    displayProductData("Updated: ", cachedRs);
                }
            }
            cachedRs.acceptChanges();
            cachedRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
