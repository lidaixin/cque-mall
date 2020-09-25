package cn.edu.cque.mall.entity;

import cn.edu.cque.mall.common.Column;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Order
 * @Description 订单实体类
 * @Author YoungWinter
 * @Date 2020/9/25 10:30
 * @Version 1.0
 **/
@Data
@ToString
public class Order {
    @Column("oid")
    private String id;
    @Column("ordertime")
    private Date createTime;
    @Column("total")
    private Double total;
    @Column("state")
    private Integer state;
    @Column("address")
    private String address;
    @Column("name")
    private String name;
    @Column("telephone")
    private String telephone;
    @Column("uid")
    private String uid;

    // 描述表与表之间的关系
    private List<OrderItem> orderItemList = new ArrayList<>();
}
