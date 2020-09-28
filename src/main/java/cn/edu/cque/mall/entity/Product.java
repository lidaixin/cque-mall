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
    //@Column("pid")
    private String id;
    // 商品名称
    //@Column("pname")
    private String name;
    // 商品的市场价格
    //@Column("market_price")
    private Double marketPrice;
    // 商品的商城价格
    //@Column("shop_price")
    private Double shopPrice;
    // 商品的图片URL
    //@Column("pimage")
    private String image;
    // 商品上架的时间
    //@Column("pdate")
    private Date createDate;
    // 是否热销 1 热销 0 不是热销
    //@Column("is_hot")
    private Integer isHot;
    // 商品的描述
    //@Column("pdesc")
    private String desc;
    // 是否上架 0 上架 1 下架
    //@Column("pflag")
    private Integer flag;
    // 商品的分类ID
    //@Column("cid")
    private String cid;
}
