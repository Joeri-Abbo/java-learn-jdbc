package com.skillsoft.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JDBCIntro {

    public static DataSource getMysqlDataSource() {
        MysqlDataSource mysqlDS = null;

        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setUrl("jdbc:mysql://localhost:3306");
            mysqlDS.setUser("mysql");
            mysqlDS.setPassword("mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mysqlDS;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        try (Connection con = JDBCIntro.getMysqlDataSource().getConnection()) {

            if (con != null) {
                System.out.println("The connection has been successfully established!");
            }
        } catch (SQLException e) {
            System.out.println("A connection error has occurred:");
            e.printStackTrace();
        }
    }
}