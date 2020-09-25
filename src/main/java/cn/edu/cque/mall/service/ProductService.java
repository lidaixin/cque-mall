package cn.edu.cque.mall.service;

import cn.edu.cque.mall.dao.ProductDao;
import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/22 14:18
 * @Version 1.0
 **/
public class ProductService {
    private ProductDao productDao = new ProductDao();

    public List<Product> findHot() {
        return productDao.findHot();
    }

    public List<Product> findNews() {
        return productDao.findNews();
    }

    public List<Product> findListByCid(String cid) {
        return productDao.findListByCid(cid);
    }

    public PageResult<Product> findPageResultByCid(String cid, int currentPage, int pageSize) {
        /**
         * 列表数据select * from product where cid = ? limit 开始的索引,pageSize
         * 第1页 10  ----->limit 0, 10   (currentPage-1)*pageSize
         * 第2页 10  ----->limit 10, 10
         * 第3页 10  ----->limit 20, 10
         * 总记录数select count(*) from product
         * 总页数 30  10  ---->  3
         * 总页数 28  10  ---->  2...8   3
         * 总页数 31  10  ---->  3...1   4
         **/
        // 列表数据
        int index = (currentPage - 1) * pageSize;
        List<Product> list = productDao.findListByCidAndPage(cid, index, pageSize);
        // 总记录数
        int totalNumber = productDao.findTotalNumber(cid);
        // 总页数
        int totalPage = totalNumber % pageSize == 0 ? (totalNumber / pageSize) : (totalNumber / pageSize) + 1;
        // 封装PageResult
        PageResult<Product> pageResult = new PageResult<>();
        pageResult.setList(list);
        pageResult.setTotalNumber(totalNumber);
        pageResult.setTotalPage(totalPage);
        pageResult.setCurrentPage(currentPage);
        return pageResult;
    }

    public Product findById(String id) {
        List<Product> list = productDao.findById(id);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }
}
