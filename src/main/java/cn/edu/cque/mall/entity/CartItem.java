package cn.edu.cque.mall.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName CartItem
 * @Description 购物项模型类
 * @Author YoungWinter
 * @Date 2020/9/24 14:28
 * @Version 1.0
 **/
@Data
@ToString
public class CartItem {
    // 商品信息
    private Product product;
    // 商品数量
    private Integer num;
    // 价格小计
    private Double sumPrice;

    public String getKey() {
        return product.getId();
    }
}
