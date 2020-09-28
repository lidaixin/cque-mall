package cn.edu.cque.mall.entity;

import lombok.*;

/**
 * @ClassName OrderItem
 * @Description 订单项实体类
 * @Author YoungWinter
 * @Date 2020/9/25 10:27
 * @Version 1.0
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    private String id;

    private Integer quantity;

    private Double total;

    private String pid;

    private String oid;

    private Product product;
}
