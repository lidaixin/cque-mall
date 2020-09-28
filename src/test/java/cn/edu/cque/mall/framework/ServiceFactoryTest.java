package cn.edu.cque.mall.framework;

import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.service.CategoryService;
import cn.edu.cque.mall.utils.ServiceFactory;
import org.junit.Test;

/**
 * @ClassName ServiceFactoryTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 15:27
 * @Version 1.0
 **/
public class ServiceFactoryTest {

    @Test
    public void test01() {
        CategoryService service = ServiceFactory.getService(CategoryService.class);
        Category category = service.findById("1");
        System.out.println(category);
    }

    @Test
    public void test02() {
        CategoryService service = ServiceFactory.getService(CategoryService.class);
        service.save(Category.builder().id("11").name("test01").build());
    }
}
