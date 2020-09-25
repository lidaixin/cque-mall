package cn.edu.cque.mall.test.mapper;

import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.OrderMapper;
import cn.edu.cque.mall.utils.MapperUtil;
import cn.edu.cque.mall.utils.UUIDUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @ClassName OrderMapperTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 14:10
 * @Version 1.0
 **/
public class OrderMapperTest {

    private OrderItemMapper itemMapper;
    private OrderMapper orderMapper;

    @Before
    public void beforeTest() {
        itemMapper = MapperUtil.getMapper(OrderItemMapper.class);
        orderMapper = MapperUtil.getMapper(OrderMapper.class);
    }

    @Test
    public void insertOrderTest() {
        String orderId = UUIDUtils.getId();
        int insert = orderMapper.insert(orderId, new Date(), 200.0, 1, "1");
        System.out.println(insert);
    }

    @Test
    public void itemMapperInsertTest() {
        String id = UUIDUtils.getId();
        // String id, int quantity, double total, String pid, String oid
        int insert = itemMapper.insert(id, 1, 10.0, "1", "7829227A690240D2BC5B32DEFA4F9964");
        System.out.println(insert);
    }
}
