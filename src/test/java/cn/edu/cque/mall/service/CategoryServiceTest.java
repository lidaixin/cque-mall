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
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findAllTest() {
        categoryService.findAll().forEach(System.out::println);
    }

    @Test
    public void findByIdTest() {
        System.out.println(categoryService.findById("2"));
    }
}
