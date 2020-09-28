package cn.edu.cque.mall.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @ClassName SqlSessionUtil
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/27 11:47
 * @Version 1.0
 **/
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 1 配置文件的地址
            String resource = "SqlMapConfig.xml";
            // 2 读取配置文件到InputStream中,要使用Mybatis自带的Resources类来读取文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 3 通过SqlSessionFactoryBuilder来创建SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            System.out.println("创建SqlSession失败");
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        // 4 sqlSession默认是手动提交事务,可以通过设置autoCommit为true的方式改为自动提交
        return sqlSessionFactory.openSession();
    }

    public static SqlSession getBatchSqlSession() {
        // 4 sqlSession默认是手动提交事务,可以通过设置autoCommit为true的方式改为自动提交
        return sqlSessionFactory.openSession(ExecutorType.BATCH);
    }
}
