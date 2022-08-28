package com.skillsoft.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;
import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class CachedRowSetExamples {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {

            CachedRowSet cachedRs = RowSetProvider.newFactory().createCachedRowSet();

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");

            cachedRs.populate(rs);
            con.close();

            while (cachedRs.next()) {
                System.out.print("ID: " + cachedRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + cachedRs.getString("product_name") + "\t");
                System.out.print("Price: " + cachedRs.getDouble("price") + "\n");
            }
            cachedRs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
