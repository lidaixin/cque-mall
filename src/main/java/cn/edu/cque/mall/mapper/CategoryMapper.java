package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName CategoryMapper
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 15:56
 * @Version 1.0
 **/
public interface CategoryMapper {

    // 注解+方法就会被Mybatis解析为一个statement,ID就是方法名
    // 如果sql语句及相关的配置比较简单,可以使用注解;反之,使用xml配置
    @Select("select * from category where cid = #{id}")
    Category findById(@Param("id") String id);

    @Select("select * from category")
    List<Category> findAll();

    @Insert("insert into category values(#{id}, #{name})")
    void save(Category category);
}
