package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.Category;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description CategoryService
 * @Author YoungWinter
 * @Date 2020/9/28 15:33
 * @Version 1.0
 **/
public interface CategoryService {

    List<Category> findAll();

    Category findById(String cid);

    void save(Category category);

    int deleteByCid(String cid);

    int updateByCid(Category category);
}
