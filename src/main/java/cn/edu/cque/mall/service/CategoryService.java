package cn.edu.cque.mall.service;

import cn.edu.cque.mall.dao.CategoryDao;
import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryService
 * @Description CategoryService
 * @Author YoungWinter
 * @Date 2020/9/22 10:25
 * @Version 1.0
 **/
public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        // 1 首先从redis缓存中查询
        Jedis jedis = JedisUtils.getJedis();
        String categoryListJson = jedis.get("categoryList");
        if (!StringUtils.isEmpty(categoryListJson)) {
            // 2 如果有就直接返回
            try {
                categoryList = mapper.readValue(categoryListJson, new TypeReference<List<Category>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 3 如果没有再查询数据库
            categoryList = categoryDao.findAll();
            // 4 将查询到的结果存入缓存,再返回数据
            try {
                jedis.set("categoryList", mapper.writeValueAsString(categoryList));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        // 5 释放资源
        jedis.close();
        return categoryList;
    }

    public Category findById(String cid) {
        List<Category> list = categoryDao.findById(cid);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }
}
