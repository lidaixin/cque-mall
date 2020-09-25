package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.common.Select;
import cn.edu.cque.mall.entity.User;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 10:45
 * @Version 1.0
 **/
public interface UserMapper {

    @Select("select * from user where username = ? and password = ?")
    User findByUsernameAndPassword(String username, String password);
}
