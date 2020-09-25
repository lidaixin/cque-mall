package cn.edu.cque.mall.test.mapper;

import cn.edu.cque.mall.common.MapperHandler;
import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.mapper.CategoryMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.utils.MapperUtil;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @ClassName CategoryMapperTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 16:23
 * @Version 1.0
 **/
public class CategoryMapperTest {

    @Test
    public void mapperTest01() {
        // 1 获取CategoryMapper的代理对象
        CategoryMapper categoryMapper = MapperUtil.getMapper(CategoryMapper.class);
        Category category = categoryMapper.findById("1");
        System.out.println(category);
    }

    @Test
    public void mapperTest02() {
        // 1 获取CategoryMapper的代理对象
        ProductMapper mapper = MapperUtil.getMapper(ProductMapper.class);
        Product product = mapper.findById("1");
        System.out.println(product);
    }

    @Test
    public void mapperTest03() {
        CategoryMapper mapper = MapperUtil.getMapper(CategoryMapper.class);
        List<Category> all = mapper.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void stringTest() {
        System.out.println(getSetter("name"));
    }

    private String getSetter(String name) {
        String first = String.valueOf(name.charAt(0)).toUpperCase();
        String substring = name.substring(1);
        return "set" + first + substring;
    }
}
