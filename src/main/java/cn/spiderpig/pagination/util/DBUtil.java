package cn.spiderpig.pagination.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.spiderpig.pagination.entity.DBConfig;

public class DBUtil {

    static {
        DBConfig config = DBConfigUtil.getConfig();
        DBUtil.openConnection(config);
    }

    private static Connection CONNECTION;

    private DBUtil() {
    }

    /*
     * public synchronized static Connection getConnection(DBConfig config) {
     * 
     * if (config == null) { System.out.println("数据库配置加载失败"); }
     * 
     * try { Class.forName(config.getDriver()); CONNECTION =
     * DriverManager.getConnection(config.getUrl(), config.getUsername(),
     * config.getPassword()); } catch (ClassNotFoundException e) {
     * System.out.println("数据库驱动加载失败"); } catch (SQLException e) {
     * System.out.println("创建连接失败"); }
     * 
     * return CONNECTION; }
     */

    public static void openConnection(DBConfig config) {

        if (config == null) {
            System.out.println("数据库配置加载失败");
        }

        try {
            Class.forName(config.getDriver());
            CONNECTION = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败");
        } catch (SQLException e) {
            System.out.println("创建连接失败");
        }

    }

    public synchronized static Connection getConnection() {
        return CONNECTION;
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("结果集释放失败");
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("声明释放失败");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("连接释放失败");
            }
        }

    }

}
