package cn.edu.cque.mall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName CategoryServiceTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 22:38
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderServiceTest {

    @Autowired
    private OrderService service;

    @Test
    public void findByUidTest() {
        service.findAllByUid("1").forEach(System.out::println);
    }

    @Test
    public void findByIdTest() {
        System.out.println(service.findById("5A9C0EA4DBBB4722BBFA64A0BBFB50C4"));
    }
}
