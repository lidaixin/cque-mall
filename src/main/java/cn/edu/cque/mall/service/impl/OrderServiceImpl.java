package cn.edu.cque.mall.service.impl;

import cn.edu.cque.mall.common.Dao;
import cn.edu.cque.mall.entity.*;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.OrderMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.service.OrderItemService;
import cn.edu.cque.mall.service.OrderService;
import cn.edu.cque.mall.utils.*;
import org.apache.ibatis.session.SqlSession;
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
public class OrderServiceImpl implements OrderService {

    @Dao
    private OrderMapper orderMapper;

    @Dao
    private OrderItemMapper itemMapper;

    private ProductMapper productMapper = MapperUtil.getMapper(ProductMapper.class);
    private OrderItemService orderItemService = new OrderItemService();

    /**
     * 1 根据购物车创建一个Order对象
     * 2 调用OrderMapper的save方法保存Order
     * 3 根据购物车创建OrderItem的列表
     * 4 调用OrderItemMapper的save方法保存列表
     * 5 注意：需要事务控制
     */
    public Order saveOrder(User user, Cart cart) {
        // 1 创建SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getBatchSqlSession();
        // 2 通过SqlSession创建OrderMapper和OrderItemMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        OrderItemMapper itemMapper = sqlSession.getMapper(OrderItemMapper.class);
        // 3 根据购物车创建一个Order对象
        // 3.1 生成订单ID
        String orderId = UUIDUtils.getId();
        try {
            // 3.2 创建订单
            Order order = Order.builder()
                    .id(orderId)
                    .createTime(new Date())
                    .state(OrderStatus.NON_PAYMENT.getCode())
                    .total(cart.getTotalPrice())
                    .uid(user.getId()).build();
            // 4 调用OrderMapper的save方法保存Order
            orderMapper.save(order);
            // 5 根据购物车创建OrderItem的列表,调用OrderItemMapper的save方法保存列表
            cart.getList().stream().map(cartItem -> OrderItem.builder()
                    .id(UUIDUtils.getId())
                    .total(cartItem.getSumPrice())
                    .quantity(cartItem.getNum())
                    .pid(cartItem.getKey())
                    .oid(orderId)
                    .build()
            ).forEach(itemMapper::save);
            sqlSession.commit();
            return orderMapper.findById(orderId);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    public void updateOrderInfo(Order order) {
        // 1 创建SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.getBatchSqlSession();
        // 2 通过SqlSession创建OrderMapper和OrderItemMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            // 3 更新Order
            orderMapper.updateOrderInfo(order);
            // 4 提交
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    //-------------------------------------------------------------------------
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
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.findById(id);
        sqlSession.close();
        return order;
    }
}
