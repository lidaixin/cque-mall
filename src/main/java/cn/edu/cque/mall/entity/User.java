package cn.edu.cque.mall.entity;

import cn.edu.cque.mall.common.Column;
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

    //@Column("uid")
    private String id;

   // @Column("username")
    private String username;// 用户名

    //@Column("password")
    private String password;

    //@Column("name")
    private String name;//姓名

    //@Column("email")
    private String email;

    //@Column("telephone")
    private String phone;

    //@Column("birthday")
    private Date birthday;

    //@Column("sex")
    private String sex;

    //@Column("state")
    private Integer state;// 用户的状态

    //@Column("code")
    private String code;// 用户名的激活码
}
