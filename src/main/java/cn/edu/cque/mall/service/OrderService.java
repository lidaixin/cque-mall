package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.*;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.OrderMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.utils.DruidUtil;
import cn.edu.cque.mall.utils.MapperUtil;
import cn.edu.cque.mall.utils.TransactionUtil;
import cn.edu.cque.mall.utils.UUIDUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 10:39
 * @Version 1.0
 **/
public class OrderService {

    private OrderMapper orderMapper = MapperUtil.getMapper(OrderMapper.class);
    private OrderItemMapper itemMapper = MapperUtil.getMapper(OrderItemMapper.class);
    private ProductMapper productMapper = MapperUtil.getMapper(ProductMapper.class);
    private OrderItemService orderItemService = new OrderItemService();

    public Order createOrder(User user, Cart cart) {
        // Spring的事务管理器
        DataSourceTransactionManager manager = new DataSourceTransactionManager(DruidUtil.getDataSource());
        // Spring的事务模板类对象
        TransactionTemplate txTemplate = new TransactionTemplate(manager);
        return txTemplate.execute(status -> {
            // connection.setAutoCommit(false)
            try {
                // 1 生成订单ID
                String orderId = UUIDUtils.getId();
                // 2 创建订单
                Date creatTime = new Date();
                orderMapper.insert(orderId, creatTime, cart.getTotalPrice(), OrderStatus.NON_PAYMENT.getCode(), user.getId());
                // 3 创建订单项
                List<CartItem> cartItemList = cart.getList();
                cartItemList.forEach(i -> {
                    // String id, int quantity, double total, String pid, String oid
                    String itemId = UUIDUtils.getId();
                    itemMapper.insert(itemId, i.getNum(), i.getSumPrice(), i.getKey(), orderId);
                });
                return findById(orderId);
                // connection.commit()
            } catch (Exception e) {
                // connection.rollback()
                status.setRollbackOnly();
            }
            return null;
        });
    }

    public void updateOrder(String id, String name, String address, String telephone) {
        TransactionTemplate txTemplate = TransactionUtil.getTxTemplate();
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    orderMapper.updateOrder(name, address, telephone, id);
                } catch (Exception e) {
                    status.setRollbackOnly();
                }
            }
        });
    }

    public List<Order> findAllByUid(String uid) {
        // 1 查询订单ID列表
        List<Order> orderList = orderMapper.findAllByUid(uid);
        // 2 查询每个订单中的订单项,然后封装到订单中去
        return orderList.stream().map(o -> o.getId()).map(this::findById).collect(Collectors.toList());
        // return idList.stream().map(this::findById).collect(Collectors.toList());
    }

    public Order findById(String id) {
        // 1 查询订单信息
        Order order = orderMapper.findById(id);
        // 2 查询订单关联的所有订单项
        List<OrderItem> orderItemList = orderItemService.findListByOid(order.getId());
        order.setOrderItemList(orderItemList);
        return order;
    }
}
