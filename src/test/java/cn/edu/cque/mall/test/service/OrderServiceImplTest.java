package cn.edu.cque.mall.test.service;

import cn.edu.cque.mall.entity.Order;
import cn.edu.cque.mall.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName OrderServiceTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 16:38
 * @Version 1.0
 **/
public class OrderServiceImplTest {

    private OrderServiceImpl service;

    @Before
    public void beforeTest() {
        service = new OrderServiceImpl();
    }

    @Test
    public void findByIdTest() {
        Order order = service.findById("FAC9760AF2014C0E874E0D0BC4671651");
        System.out.println(order);
    }

    @Test
    public void findAllTest() {
        List<Order> all = service.findAllByUid("2");
        all.forEach(System.out::println);
    }
}
