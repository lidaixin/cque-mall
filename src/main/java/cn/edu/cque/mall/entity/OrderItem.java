package cn.edu.cque.mall.entity;

import cn.edu.cque.mall.common.Column;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName OrderItem
 * @Description 订单项实体类
 * @Author YoungWinter
 * @Date 2020/9/25 10:27
 * @Version 1.0
 **/
@Data
@ToString
public class OrderItem {
    @Column("itemid")
    private String id;
    @Column("quantity")
    private Integer quantity;
    @Column("total")
    private Double total;
    @Column("pid")
    private String pid;
    @Column("oid")
    private String oid;

    private Product product;
}
