package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName ProductMapper
 * @Description
 * @Author YoungWinter
 * @Date 2020/9/23 16:33
 * @Version 1.0
 **/
public interface ProductMapper {

    Product findById(@Param("id") String id);

    List<Product> findAllByCid(@Param("cid")String cid);

    List<Product> findHotList();

    List<Product> findNewsList();

    @Update("update product set pflag = 1 where pid = #{id}")
    int deleteById(@Param("id") String id);

    int insertProduct(Product product);
}
