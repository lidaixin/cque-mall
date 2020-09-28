package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.utils.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/22 14:18
 * @Version 1.0
 **/
public class ProductService {
    // private ProductDao productDao = new ProductDao();

    public List<Product> findHot() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> hotList = mapper.findHotList();
        sqlSession.close();
        return hotList;
    }

    public List<Product> findNews() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> newsList = mapper.findNewsList();
        sqlSession.close();
        return newsList;
    }

//    public List<Product> findListByCid(String cid) {
//        return productDao.findListByCid(cid);
//    }

    public PageResult<Product> findPageResultByCid(String cid, int currentPage, int pageSize) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        PageHelper.startPage(currentPage, pageSize);
        List<Product> productList = mapper.findListByCid(cid);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        PageResult<Product> pageResult = new PageResult<>();
        pageResult.setList(pageInfo.getList());
        pageResult.setCurrentPage(currentPage);
        pageResult.setTotalNumber((int) pageInfo.getTotal());
        pageResult.setTotalPage(pageInfo.getPages());
        sqlSession.close();
        return pageResult;
    }

    public Product findById(String id) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        Product product = mapper.findById(id);
        sqlSession.close();
        return product;
    }
}
