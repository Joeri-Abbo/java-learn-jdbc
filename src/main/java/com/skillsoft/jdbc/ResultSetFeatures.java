package com.skillsoft.jdbc;

import java.sql.*;

public class ResultSetFeatures {
    public static String dbURL = "jdbc:mysql://localhost:3306/";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {


        try (Connection con = DriverManager.getConnection(dbURL, username, password)) {
            DatabaseMetaData metaData = con.getMetaData();

            System.out.println("-- ResultSet Properties --\n");
            System.out.println("Support TYPE_FORWARD_ONLY:" + metaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
            System.out.println("Support TYPE_SCROLL_INSENSITIVE:" + metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
            System.out.println("Support TYPE_SCROLL_SENSITIVE:" + metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));

            System.out.println("\n\n-- ResultSet Concurrency --\n");
            System.out.println("Support CONCUR_READ_ONLY:" + metaData.supportsResultSetType(ResultSet.CONCUR_READ_ONLY));
            System.out.println("Support CONCUR_UPDATABLE:" + metaData.supportsResultSetType(ResultSet.CONCUR_UPDATABLE));


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
