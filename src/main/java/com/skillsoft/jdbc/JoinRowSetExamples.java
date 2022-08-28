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

            cachedRsProd.setCommand("SELECT * FROM Products");
            cachedRsProdBrand.setCommand("SELECT * FROM ProductBrand");

            cachedRsProd.execute(con);
            cachedRsProdBrand.execute(con);

            JoinRowSet joinRs = RowSetProvider.newFactory().createJoinRowSet();

            System.out.println("<<<< Supported Joins >>>>");
            System.out.println("Inner join: " + joinRs.supportsInnerJoin());
            System.out.println("Left join: " + joinRs.supportsLeftOuterJoin());
            System.out.println("Right join: " + joinRs.supportsRightOuterJoin());
            System.out.println("Cross join: " + joinRs.supportsCrossJoin());
            System.out.println("Full join: " + joinRs.supportsFullJoin());
            joinRs.addRowSet(cachedRsProd, "product_id");
            joinRs.addRowSet(cachedRsProdBrand, "product_id");

            System.out.println("------Products in stock------");

            while (joinRs.next()) {
                System.out.print("Brand name: " + joinRs.getString("brand_name") + "\t");
                System.out.print("Product name: " + joinRs.getString("product_name") + "\t");
                System.out.print("Price: " + joinRs.getDouble("price") + "\n");
            }

            joinRs.close();
            cachedRsProd.close();
            cachedRsProdBrand.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
