package cn.edu.cque.mall.test.mapper;

import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.utils.MapperUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName ProductMapperTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 17:23
 * @Version 1.0
 **/
public class ProductMapperTest {

    private ProductMapper mapper;

    @Before
    public void beforeTest() {
        mapper = MapperUtil.getMapper(ProductMapper.class);
    }

    @Test
    public void test01() {
        List<Product> hot = mapper.findHot(1, 0, 8);
        hot.forEach(System.out::println);
    }
}
