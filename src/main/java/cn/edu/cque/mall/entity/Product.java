package cn.edu.cque.mall.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName Product
 * @Description Product实体类
 * @Author YoungWinter
 * @Date 2020/9/22 14:10
 * @Version 1.0
 **/
@Data
@ToString
public class Product {
    // ID
    private String id;
    // 商品名称
    private String name;
    // 商品的市场价格
    private Double marketPrice;
    // 商品的商城价格
    private Double shopPrice;
    // 商品的图片URL
    private String image;
    // 商品上架的时间
    private Date createDate;
    // 是否热销 1 热销 0 不是热销
    private Integer isHot;
    // 商品的描述
    private String desc;
    // 是否上架 0 上架 1 下架
    private Integer flag;
    // 商品的分类ID
    private String cid;
}
