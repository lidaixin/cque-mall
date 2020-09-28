package cn.edu.cque.mall.service.impl;

import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/22 14:18
 * @Version 1.0
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> findHot() {
        return productMapper.findHotList();
    }

    public List<Product> findNews() {
        return productMapper.findNewsList();
    }

    public PageResult<Product> findPageResultByCid(String cid, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Product> productList = productMapper.findAllByCid(cid);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        PageResult<Product> result = new PageResult<>();
        result.setTotalPage(pageInfo.getPages());
        result.setCurrentPage(pageInfo.getPageNum());
        result.setTotalNumber((int)pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    public Product findById(String id) {
        return productMapper.findById(id);
    }
}
