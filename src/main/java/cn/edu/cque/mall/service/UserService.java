package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.User;
import cn.edu.cque.mall.mapper.UserMapper;
import cn.edu.cque.mall.utils.MapperUtil;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 10:45
 * @Version 1.0
 **/
public class UserService {

    private UserMapper userMapper = MapperUtil.getMapper(UserMapper.class);

    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }
}
