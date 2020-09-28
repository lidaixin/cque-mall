package cn.edu.cque.mall.test.mybatis;

import cn.edu.cque.mall.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/27 16:01
 * @Version 1.0
 **/
public class MybatisTest {

    @Test
    public void test01() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        System.out.println(sqlSession);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
