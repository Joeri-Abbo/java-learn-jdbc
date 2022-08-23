package com.skillsoft.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDBUtil {

    public static Connection getConnection() throws SQLException {
        Connection con = null;

        try (InputStream in = MySQLDBUtil.class.getResourceAsStream("/mysqldb.properties")) {

            Properties props = new Properties();
            props.load(in);

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            con = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }
}
