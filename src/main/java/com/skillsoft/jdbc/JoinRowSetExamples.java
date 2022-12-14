package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

public class JoinRowSetExamples {

    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            CachedRowSet cachedRsProd = RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet cachedRsProdBrand = RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet cachedRsProdSub = RowSetProvider.newFactory().createCachedRowSet();

            cachedRsProd.setCommand("SELECT * FROM Products");
            cachedRsProdBrand.setCommand("SELECT * FROM ProductBrand");
            cachedRsProdSub.setCommand("SELECT * FROM ProductSupplier");

            cachedRsProd.execute(con);
            cachedRsProdBrand.execute(con);
            cachedRsProdSub.execute(con);

            JoinRowSet joinRs = RowSetProvider.newFactory().createJoinRowSet();

            joinRs.addRowSet(cachedRsProd, "product_id");
            joinRs.addRowSet(cachedRsProdBrand, "product_id");
            joinRs.addRowSet(cachedRsProdSub, "product_id");

            System.out.println("------Products in stock------");

            while (joinRs.next()) {
                System.out.print("Brand name: " + joinRs.getString("brand_name") + "\t");
                System.out.print("Product name: " + joinRs.getString("product_name") + "\t");
                System.out.print("Supplier name: " + joinRs.getString("supplier_name") + "\t");
                System.out.print("Price: " + joinRs.getDouble("price") + "\n");
            }

            joinRs.close();
            cachedRsProd.close();
            cachedRsProdBrand.close();
            cachedRsProdSub.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
