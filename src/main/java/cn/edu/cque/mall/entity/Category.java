package cn.edu.cque.mall.entity;

import lombok.*;

/**
 * @ClassName Category
 * @Description Category实体类
 * @Author YoungWinter
 * @Date 2020/9/22 10:16
 * @Version 1.0
 **/
@NoArgsConstructor// 无参构造器
@AllArgsConstructor// 全参构造器
@Data// 自动生成setter和getter
@ToString// 自动生成toString()
@Builder
public class Category {

    // @Column("cid")
    private String id;
    // @Column("cname")
    private String name;
}
