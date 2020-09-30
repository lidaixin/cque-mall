package cn.edu.cque.mall.entity;

import lombok.Data;

import java.util.*;

/**
 * @ClassName Cart
 * @Description 购物车模型类
 * @Author YoungWinter
 * @Date 2020/9/24 14:30
 * @Version 1.0
 **/
@Data
public class Cart {

    // 购物项集合
    private Map<String, CartItem> itemMap = new HashMap<>();

    // 商品数量
    private Integer totalNum;

    // 价格总计
    private Double totalPrice;

    // 将商品放入购物车
    public void addCartItem(CartItem cartItem) {
        // 1 获取商品ID作为键值
        String key = cartItem.getKey();
        // 2 如果之前没有该件商品
        if (!itemMap.containsKey(key)) {
            itemMap.put(key, cartItem);
        } else {
            // 3 如果购物车中已经有了商品,修改数量和价格小计
            CartItem oldItem = itemMap.get(key);
            int newNum = cartItem.getNum() + oldItem.getNum();
            if (newNum <= 0) newNum = 1;
            double newSumPrice = cartItem.getProduct().getShopPrice() * newNum;
            cartItem.setNum(newNum);
            cartItem.setSumPrice(newSumPrice);
            itemMap.put(key, cartItem);
        }
        // 3 计算购物车中的商品数量及总价
        freshData();
    }

    // 删除购物项
    public void delCartItem(String key) {
        itemMap.remove(key);
        if (itemMap.size() == 0) {
            totalNum = 0;
            totalPrice = 0.0;
            return;
        }
        freshData();
    }

    // 清空购物车
    public void clearCart() {
        itemMap.clear();
    }

    public List<CartItem> getList() {
        return new ArrayList<>(itemMap.values());
    }

    private void freshData() {
        totalNum = itemMap.values().stream().mapToInt(c -> c.getNum()).reduce((a, b) -> a + b).getAsInt();
        totalPrice = itemMap.values().stream().mapToDouble(c -> c.getSumPrice()).reduce((a, b) -> a + b).getAsDouble();
    }
}
