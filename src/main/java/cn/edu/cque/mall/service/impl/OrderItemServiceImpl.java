package cn.edu.cque.mall.service.impl;

import cn.edu.cque.mall.entity.OrderItem;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.service.OrderItemService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderItemService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 16:44
 * @Version 1.0
 **/
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper itemMapper;

    public List<OrderItem> findListByOid(String oid) {
        return itemMapper.findAllByOid(oid);
    }

}
