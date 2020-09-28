package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 22:32
 * @Version 1.0
 **/
public interface UserService {
    User login(String username, String password);
}
