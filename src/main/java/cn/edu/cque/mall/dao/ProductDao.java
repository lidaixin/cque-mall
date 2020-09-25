package cn.edu.cque.mall.dao;

import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.utils.DruidUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ProductDao
 * @Description ProductDao
 * @Author YoungWinter
 * @Date 2020/9/22 14:17
 * @Version 1.0
 **/
public class ProductDao {
    private JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());

    // 将数据库中的数据封装成实体类的一个内部类
    private class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getString("pid"));
            product.setName(rs.getString("pname"));
            product.setMarketPrice(rs.getDouble("market_price"));
            product.setShopPrice(rs.getDouble("shop_price"));
            product.setImage("http://192.168.11.44:9090" + rs.getString("pimage"));
            product.setCreateDate(rs.getDate("pdate"));
            product.setIsHot(rs.getInt("is_hot"));
            product.setDesc(rs.getString("pdesc"));
            product.setFlag(rs.getInt("pflag"));
            product.setCid(rs.getString("cid"));
            return product;
        }
    }

    /***
     * @Author YoungWinter
     * @Description 根据id查询数据
     * @Date 10:45 2020/9/23
     * @Param [id]
     * @return java.util.List<cn.edu.cque.mall.entity.Product>
     **/
    public List<Product> findById(String id) {
        // 1 获取连接
        // 2 获取ps
        // 3 编写SQL语句
        // 4 执行SQL
        // 5 处理结果
        // 6 释放释放
        return template.query("select * from product where pid = ?", new ProductRowMapper(), id);
    }

    /***
     * @Author YoungWinter
     * @Description 查询总记录数
     * @Date 9:16 2020/9/23
     * @Param []
     * @return int
     **/
    public int findTotalNumber(String cid) {
        return template.queryForObject("select count(*) from product where cid = ?", int.class, cid);
    }

    /***
     * @Author YoungWinter
     * @Description 根据cid和分页信息查询商品列表
     * @Date 9:12 2020/9/23
     * @Param [cid, index, pageSize]
     * @return java.util.List<cn.edu.cque.mall.entity.Product>
     **/
    public List<Product> findListByCidAndPage(String cid, int index, int pageSize) {
        return template.query("select * from product where cid = ? limit ?, ?",
                new ProductRowMapper(),
                cid, index, pageSize);
    }

    /***
     * @Author YoungWinter
     * @Description 查询热销商品
     * @Date 15:29 2020/9/22
     * @Param []
     * @return java.util.List<cn.edu.cque.mall.entity.Product>
     **/
    public List<Product> findHot() {
        String sql = "select * from product where is_hot = ? limit ?, ?";
        return template.query(sql, new ProductRowMapper(), 1, 0, 8);
    }

    /***
     * @Author YoungWinter
     * @Description 查询最新商品
     * @Date 15:29 2020/9/22
     * @Param []
     * @return java.util.List<cn.edu.cque.mall.entity.Product>
     **/
    public List<Product> findNews() {
        String sql = "select * from product order by pdate desc limit ?, ?";
        return template.query(sql, new ProductRowMapper(), 0, 8);
    }

    /***
     * @Author YoungWinter
     * @Description 根据cid查询商品列表
     * @Date 15:30 2020/9/22
     * @Param [cid]
     * @return java.util.List<cn.edu.cque.mall.entity.Product>
     **/
    public List<Product> findListByCid(String cid) {
        String sql = "select * from product where cid = ?";
        return template.query(sql, new ProductRowMapper(), cid);
    }
}
