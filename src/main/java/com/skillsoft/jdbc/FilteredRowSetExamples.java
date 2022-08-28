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
            ProductPriceFilter ppFilter = new ProductPriceFilter(5.0, 15.0, 3);
            filteredRs.setFilter(ppFilter);

            filteredRs.moveToInsertRow();

            filteredRs.updateInt("product_id", 108);
            filteredRs.updateString("product_name", "Curved LCD Monitor");
            filteredRs.updateDouble("price", 199);

            filteredRs.insertRow();
            filteredRs.moveToCurrentRow();

            filteredRs.acceptChanges();
            filteredRs.beforeFirst();

            System.out.println("--- Filtered RowSet ---");
            while (filteredRs.next()) {
                System.out.print("ID: " + filteredRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + filteredRs.getString("product_name") + "\t");
                System.out.print("Price: " + filteredRs.getDouble("price") + "\n");
            }

            filteredRs.commit();
            filteredRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
