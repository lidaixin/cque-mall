package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.User;
import cn.edu.cque.mall.mapper.UserMapper;
import cn.edu.cque.mall.utils.MapperUtil;
import cn.edu.cque.mall.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 10:45
 * @Version 1.0
 **/
public class UserService {

    // private UserMapper userMapper = MapperUtil.getMapper(UserMapper.class);

    public User login(String username, String password) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findByUsernameAndPassword(username, password);
        sqlSession.close();
        return user;
    }
}
