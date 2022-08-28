package com.skillsoft.jdbc;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetProvider;

public class FilteredRowSetExamples {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) {
        FilteredRowSet filteredRs = null;
        try {

            filteredRs = RowSetProvider.newFactory().createFilteredRowSet();

            filteredRs.setUsername(username);
            filteredRs.setPassword(password);
            filteredRs.setUrl(dbURL);

            filteredRs.setCommand("SELECT * FROM Products");
            filteredRs.execute();
            System.out.println("--- Unfiltered RowSet ---");

            while (filteredRs.next()) {
                System.out.print("ID: " + filteredRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + filteredRs.getString("product_name") + "\t");
                System.out.print("Price: " + filteredRs.getDouble("price") + "\n");
            }

            ProductPriceFilter ppFilter = new ProductPriceFilter(5.0, 15.0, 3);

            filteredRs.beforeFirst();
            filteredRs.setFilter(ppFilter);

            System.out.println("--- Filtered RowSet ---");
            while (filteredRs.next()) {
                System.out.print("ID: " + filteredRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + filteredRs.getString("product_name") + "\t");
                System.out.print("Price: " + filteredRs.getDouble("price") + "\n");
            }


            filteredRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
