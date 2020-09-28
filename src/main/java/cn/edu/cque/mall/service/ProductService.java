package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 22:31
 * @Version 1.0
 **/
public interface ProductService {

    List<Product> findHot();

    List<Product> findNews();

    PageResult<Product> findPageResultByCid(String cid, int currentPage, int pageSize);

    Product findById(String id);
}
