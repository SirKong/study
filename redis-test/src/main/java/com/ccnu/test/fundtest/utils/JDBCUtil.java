package com.ccnu.test.fundtest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    public static final String url = "jdbc:mysql://127.0.0.1/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "ccnu";

    public static Connection getConnection() {
        try {
            Class.forName(name);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }//指定连接类型
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }//获取连接
        return conn;
    }


    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
