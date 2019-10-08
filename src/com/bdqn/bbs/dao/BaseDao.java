package com.bdqn.bbs.dao;

/**
 * @author: 赖榕
 * @date: 2019/9/5
 * @description:
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: dao
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {

    protected static Connection connection;

    static {
        Properties properties = new Properties();
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("conf\\database.properties");
        try {
            System.out.println(inputStream);
            properties.load(inputStream);
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 填充预编译对象的占位符
     *
     * @param ps   预编译对象
     * @param args 占位符参数
     */
    protected void full(PreparedStatement ps, Object[] args) throws Exception {
        if (args == null) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            try {
                ps.setObject(i + 1, args[i]);
            } catch (SQLException e) {
                throw new Exception("填充预编译对象出错");
            }
        }

    }

    protected ResultSet query(String sql, Object[] args) throws Exception {
        PreparedStatement ps = connection.prepareStatement(sql);
        this.full(ps, args);
        ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }

    protected void update(String sql, Object[] args) throws Exception {
        PreparedStatement ps = connection.prepareStatement(sql);
        this.full(ps, args);
        ps.executeUpdate();
    }

    public static void close(PreparedStatement ps, ResultSet rs) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
