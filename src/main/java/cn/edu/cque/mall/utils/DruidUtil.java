package cn.edu.cque.mall.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @ClassName DruidUtil
 * @Description Druid连接池工具类
 * @Author Winter
 * @Date 2/17 15:59
 **/
public class DruidUtil {

    // 使用静态代码块初始化DataSource对象
    // 获取连接的方法
    // 获取连接池的方法
    // 释放资源的方法

    private static DataSource ds;

    static {
        try {
            InputStream resource = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(resource);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Winter
     * @Description 获取连接
     * @Date 16:03 2/17
     * @Param []
     * @return java.sql.Connection
     **/
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * @Author Winter
     * @Description 获取连接池
     * @Date 16:03 2/17
     * @Param []
     * @return javax.sql.DataSource
     **/
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * @Author Winter
     * @Description 释放资源
     * @Date 16:05 2/17
     * @Param [resultSet, statement, connection]
     * @return void
     **/
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
