package cn.edu.cque.mall.entity;

import lombok.*;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String id;
    private Date createTime;
    private Double total;
    private Integer state;
    private String address;
    private String name;
    private String telephone;
    private String uid;

    // 描述表与表之间的关系
    private List<OrderItem> orderItemList = new ArrayList<>();
}
