package cn.edu.cque.mall.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {
    //创建连接池
    private static JedisPoolConfig config;
    private static JedisPool pool;

    static {
        try {
            InputStream resource = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
            Properties properties = new Properties();
            properties.load(resource);
            config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(properties.getProperty("jedis.maxTotal")));
            config.setMaxIdle(Integer.parseInt(properties.getProperty("jedis.maxIdle")));
            pool = new JedisPool(config, properties.getProperty("jedis.host"), Integer.parseInt(properties.getProperty("jedis.port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取连接的方法
    public static Jedis getJedis() {
        return pool.getResource();
    }


    //释放连接
    public static void closeJedis(Jedis j) {
        j.close();
    }
}
