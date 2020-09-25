package cn.edu.cque.mall.test.service;

import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.service.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName ProductServiceTest
 * @Description ProductService测试类
 * @Author YoungWinter
 * @Date 2020/9/22 14:34
 * @Version 1.0
 **/
public class ProductServiceTest {

    private ProductService service;

    @Before
    public void beforeTest() {
        service = new ProductService();
    }

    @Test
    public void findHotTest() {
        List<Product> productList = service.findHot();
        productList.forEach(System.out::println);
    }

    @Test
    public void findNewsTest() {
        List<Product> productList = service.findNews();
        productList.forEach(System.out::println);
    }

    @Test
    public void findListByCidTest() {
        List<Product> productList = service.findListByCid("1");
        productList.forEach(System.out::println);
    }

    @Test
    public void findPageResultByCidTest() {
        PageResult<Product> result = service.findPageResultByCid("2", 1, 6);
        System.out.println("当前页:" + result.getCurrentPage());
        System.out.println("总页数:" + result.getTotalPage());
        System.out.println("总记录数:" + result.getTotalNumber());
        result.getList().forEach(System.out::println);
    }

    @Test
    public void findByIdTest() {
        Product product = service.findById("3");
        System.out.println(product);
    }

}
