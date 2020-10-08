package cn.edu.cque.mall.service.impl;

import cn.edu.cque.mall.entity.*;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.OrderMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.service.OrderService;
import cn.edu.cque.mall.utils.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderService
 * @Description OrderService
 * @Author YoungWinter
 * @Date 2020/9/25 10:39
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper itemMapper;

    /**
     * 1 根据购物车创建一个Order对象
     * 2 调用OrderMapper的save方法保存Order
     * 3 根据购物车创建OrderItem的列表
     * 4 调用OrderItemMapper的save方法保存列表
     * 5 注意：需要事务控制
     */
    @Transactional
    public Order saveOrder(User user, Cart cart) {
        // 1 根据购物车创建一个Order对象
        // 1.1 生成订单ID
        String orderId = UUIDUtils.getId();
        // 1.2 创建订单
        Order order = Order.builder()
                .id(orderId)
                .createTime(new Date())
                .state(OrderStatus.NON_PAYMENT.getCode())
                .total(cart.getTotalPrice())
                .uid(user.getId()).build();
        // 2 调用OrderMapper的save方法保存Order
        orderMapper.save(order);
        // 3 根据购物车创建OrderItem的列表,调用OrderItemMapper的save方法保存列表
        List<OrderItem> itemList = cart.getList().stream().map(cartItem -> OrderItem.builder()
                .id(UUIDUtils.getId())
                .total(cartItem.getSumPrice())
                .quantity(cartItem.getNum())
                .pid(cartItem.getKey())
                .oid(orderId)
                .build()
        ).collect(Collectors.toList());
        itemMapper.saveBatch(itemList);
        // 4 清空购物车
        cart.clearCart();
        return orderMapper.findById(orderId);
    }

    @Transactional
    public void updateOrderInfo(Order order) {
        orderMapper.updateOrderInfo(order);
    }

    public List<Order> findAllByUid(String uid) {
        return orderMapper.findAllByUid(uid);
    }

    public Order findById(String id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public int deleteById(String oid) {
        return orderMapper.deleteById(oid);
    }

}
