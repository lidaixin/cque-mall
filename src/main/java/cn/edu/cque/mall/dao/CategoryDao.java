package cn.edu.cque.mall.dao;

import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.utils.DruidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName CategoryDao
 * @Description CategoryDao
 * @Author YoungWinter
 * @Date 2020/9/22 10:22
 * @Version 1.0
 **/
public class CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());

    public List<Category> findAll() {
        return template.query("select * from category", new BeanPropertyRowMapper<>(Category.class));
    }

    public List<Category> findById(String cid) {
        return template.query("select * from category where cid = ?", new BeanPropertyRowMapper<>(Category.class), cid);
    }
}
