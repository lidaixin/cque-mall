package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.Cart;
import cn.edu.cque.mall.entity.Order;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.entity.User;

import java.util.List;

/**
 * @ClassName OrderService
 * @Description OrderService
 * @Author YoungWinter
 * @Date 2020/9/28 14:31
 * @Version 1.0
 **/
public interface OrderService {

    Order saveOrder(User user, Cart cart);

    void updateOrderInfo(Order order);

    List<Order> findAllByUid(String uid);

    Order findById(String id);

    List<Order> findAll();

    int deleteById(String oid);
}
