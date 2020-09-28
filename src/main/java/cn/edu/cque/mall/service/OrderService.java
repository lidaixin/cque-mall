package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.Cart;
import cn.edu.cque.mall.entity.Order;
import cn.edu.cque.mall.entity.User;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 14:31
 * @Version 1.0
 **/
public interface OrderService {

    Order saveOrder(User user, Cart cart);

    void updateOrderInfo(Order order);
}
