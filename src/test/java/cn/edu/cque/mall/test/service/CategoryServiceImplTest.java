package cn.edu.cque.mall.test.service;

import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName CategoryServiceTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/22 10:41
 * @Version 1.0
 **/
public class CategoryServiceImplTest {

    private CategoryServiceImpl service;

    @Before
    public void beforeTest() {
        service = new CategoryServiceImpl();
    }

    @Test
    public void findAllTest() {
        List<Category> all = service.findAll();
        all.forEach(System.out::println);
    }
}
