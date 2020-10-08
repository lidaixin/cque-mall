package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName CategoryMapper
 * @Description
 * @Author YoungWinter
 * @Date 2020/9/23 15:56
 * @Version 1.0
 **/
public interface CategoryMapper {

    // 注解+方法就会被Mybatis解析为一个statement,ID就是方法名
    // 如果sql语句及相关的配置比较简单,可以使用注解;反之,使用xml配置
    @Select("select * from category where cid = #{cid}")
    Category findById(@Param("cid") String cid);

    @Select("select * from category")
    List<Category> findAll();

    @Insert("insert into category values(#{cid}, #{cname})")
    void save(Category category);

    @Delete("delete from category where cid = #{id}")
    int deleteByCid(String cid);

    @Update("update category set cname=#{cname} where cid = #{cid}")
    int updateByCid(Category category);
}
