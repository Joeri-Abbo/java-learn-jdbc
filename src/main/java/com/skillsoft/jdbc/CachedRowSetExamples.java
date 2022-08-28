package com.skillsoft.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;
import javax.sql.RowSet;

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

        try {
            CachedRowSet cachedRs = RowSetProvider.newFactory().createCachedRowSet();

            cachedRs.setCommand("SELECT * FROM Products");
            cachedRs.setUrl(dbURL);
            cachedRs.setUsername(username);
            cachedRs.setPassword(password);

            cachedRs.execute();

            System.out.println("-----Navigating the cached RowSet-----");

            cachedRs.first();
            displayProductData("First()", cachedRs);

            cachedRs.relative(3);
            displayProductData("Relative(3)", cachedRs);

            cachedRs.previous();
            displayProductData("Previous()", cachedRs);

            cachedRs.absolute(6);
            displayProductData("Absolute(6)", cachedRs);

            cachedRs.last();
            displayProductData("Last()", cachedRs);

            cachedRs.relative(-5);
            displayProductData("Relative(-5)", cachedRs);

            cachedRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
