package com.skillsoft.jdbc;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;

public class FilteredRowSetExamples {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) {
        FilteredRowSet filteredRs = null;
        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            con.setAutoCommit(false);
            filteredRs = RowSetProvider.newFactory().createFilteredRowSet();


            filteredRs.setCommand("SELECT * FROM Products");
            filteredRs.execute(con);
            ProductPriceFilter ppFilter = new ProductPriceFilter(0.0, 20.0, 3);
            filteredRs.setFilter(ppFilter);
            filteredRs.beforeFirst();

            System.out.println("--- Filtered RowSet BEFORE Deletes ---");
            while (filteredRs.next()) {
                System.out.print("ID: " + filteredRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + filteredRs.getString("product_name") + "\t");
                System.out.print("Price: " + filteredRs.getDouble("price") + "\n");
            }

            filteredRs.beforeFirst();

            System.out.println("\n About to delete cables...");

            while (filteredRs.next()) {
                if (filteredRs.getString("product_name").endsWith("Cable")) {
                    filteredRs.deleteRow();
                }
            }

            filteredRs.beforeFirst();
            System.out.println("--- Filtered RowSet AFTER Deletes ---");
            while (filteredRs.next()) {
                System.out.print("ID: " + filteredRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + filteredRs.getString("product_name") + "\t");
                System.out.print("Price: " + filteredRs.getDouble("price") + "\n");
            }

            filteredRs.acceptChanges();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
