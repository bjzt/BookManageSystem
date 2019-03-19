package com.BookManageSystem.util;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBUtil {
    //	static String ip="172.0.0.1";
//	static int port = 3036;
    static String ip;
    static String database;//数据库名称
    static String endoing;
    static String loginName;
    static String password;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("database", Locale.getDefault());
        loginName = rb.getString("username");
        password = rb.getString("password");
        ip = rb.getString("host");
        database = rb.getString("database");
        endoing = rb.getString("endoing");
    }
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
