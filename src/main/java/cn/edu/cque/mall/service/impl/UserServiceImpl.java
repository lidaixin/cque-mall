package cn.edu.cque.mall.service.impl;

import cn.edu.cque.mall.entity.User;
import cn.edu.cque.mall.mapper.UserMapper;
import cn.edu.cque.mall.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description UserService
 * @Author YoungWinter
 * @Date 2020/9/24 10:45
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }
}
