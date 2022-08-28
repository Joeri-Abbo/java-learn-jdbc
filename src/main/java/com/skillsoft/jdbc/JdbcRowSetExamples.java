package com.skillsoft.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.JdbcRowSet;

public class JdbcRowSetExamples {
    public static String dbURL = "jdbc:mysql://localhost:3306/SampleDB";
    public static String username = "mysql";
    public static String password = "mysql";

    public static void main(String[] args) throws SQLException {

        try {
            JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet();

            jdbcRs.setCommand("SELECT * FROM Products");

            jdbcRs.setUrl(dbURL);
            jdbcRs.setUsername(username);
            jdbcRs.setPassword(password);

            jdbcRs.execute();

            System.out.println("-----Products in stock-----");

            while (jdbcRs.next()) {
                System.out.print("ID: " + jdbcRs.getInt("product_id") + "\t");
                System.out.print("Product name: " + jdbcRs.getString("product_name") + "\t");
                System.out.print("Price: " + jdbcRs.getDouble("price") + "\n");
            }

            jdbcRs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
