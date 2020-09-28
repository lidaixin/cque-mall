package cn.edu.cque.mall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findHot() {
        productService.findHot().forEach(System.out::println);
    }

    @Test
    public void findNews() {
    }

    @Test
    public void findPageResultByCid() {
    }

    @Test
    public void findById() {
    }
}