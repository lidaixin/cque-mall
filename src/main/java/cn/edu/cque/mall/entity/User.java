package cn.edu.cque.mall.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 10:40
 * @Version 1.0
 **/
@Data
@ToString
public class User {

    private String id;

    private String username;// 用户名

    private String password;

    private String name;//姓名

    private String email;

    private String phone;

    private Date birthday;

    private String sex;

    private Integer state;// 用户的状态

    private String code;// 用户名的激活码
}
