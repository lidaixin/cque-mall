package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description
 * @Author YoungWinter
 * @Date 2020/9/24 10:45
 * @Version 1.0
 **/
public interface UserMapper {

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
