package cn.edu.cque.mall.service.impl;

import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.mapper.CategoryMapper;
import cn.edu.cque.mall.service.CategoryService;
import cn.edu.cque.mall.utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categorymapper;

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
            categoryList = categorymapper.findAll();
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
        return categorymapper.findById(cid);
    }

    @Override
    @Transactional
    public void save(Category category) {
        categorymapper.save(category);
    }
}
