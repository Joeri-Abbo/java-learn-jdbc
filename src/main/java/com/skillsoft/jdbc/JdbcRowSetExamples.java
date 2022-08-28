package com.skillsoft.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.RowSet;

public class JdbcRowSetExamples {
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
            JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet();

            jdbcRs.setCommand("SELECT * FROM Products");
            jdbcRs.setUrl(dbURL);
            jdbcRs.setUsername(username);
            jdbcRs.setPassword(password);

            jdbcRs.setAutoCommit(false);
            jdbcRs.execute();

            System.out.println("-----Inserting rows-----");
            int cableCount = 0;
            int prodCount = 0;
            while (jdbcRs.next()) {

                prodCount = Math.max(jdbcRs.getInt("product_id"), prodCount);

                if (jdbcRs.getString("product_name").endsWith("Cable")) {
                    cableCount++;
                }
            }

            if (cableCount == 0) {
                jdbcRs.moveToInsertRow();
                jdbcRs.updateInt("product_id", ++prodCount);
                jdbcRs.updateString("product_name", "HDMI Cable");
                jdbcRs.updateDouble("price", 5);

                jdbcRs.insertRow();
                jdbcRs.last();

                displayProductData("added: ", jdbcRs);
            }

            jdbcRs.commit();
            jdbcRs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
