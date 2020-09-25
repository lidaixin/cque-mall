package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.common.Insert;
import cn.edu.cque.mall.common.Select;
import cn.edu.cque.mall.entity.Category;

import java.util.List;

/**
 * @ClassName CategoryMapper
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 15:56
 * @Version 1.0
 **/
public interface CategoryMapper {

    @Select("select * from category where cid = ?")
    Category findById(String id);

    @Select("select * from category")
    List<Category> findAll();

}
