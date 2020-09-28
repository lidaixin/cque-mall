package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.OrderItem;

import java.util.List;

/**
 * @ClassName OrderItemService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 22:29
 * @Version 1.0
 **/
public interface OrderItemService {
    List<OrderItem> findListByOid(String oid);
}
