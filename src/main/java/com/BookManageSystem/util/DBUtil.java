package com.BookManageSystem.util;

import java.sql.*;

public class DBUtil {
    //	static String ip="172.0.0.1";
//	static int port = 3036;
    static String ip = "localhost";
    static String database = "yootk";//数据库名称
    static String endoing = "utf-8";
    static String loginName = "root";
    static String password = "root";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s/%s?characterEncoding=%s", ip, database, endoing);
        return DriverManager.getConnection(url,loginName,password);
    }
}
