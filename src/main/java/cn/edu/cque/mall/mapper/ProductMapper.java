package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.common.Select;
import cn.edu.cque.mall.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ProductMapper
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 16:33
 * @Version 1.0
 **/
public interface ProductMapper {

    // @Select("select * from product where pid = ?")
    Product findById(@Param("id") String id);

    List<Product> findAllByCid(@Param("cid")String cid);

    // @Select("select * from product where is_hot = ? limit ?, ?")
    List<Product> findHotList();

    // @Select("select * from product order by pdate desc limit ?, ?")
    List<Product> findNewsList();

    List<Product> findByPrice(Product product);

    //----------------------------------------------------------------------------

    @Select("select count(*) from product where cid = ?")
    int findTotalNumber(String cid);

    @Select("select * from product where cid = ? limit ?, ?")
    List<Product> findListByCidAndPage(String cid, int index, int pageSize);

    @Select("select * from product where is_hot = ? limit ?, ?")
    List<Product> findHot(int isHot, int index, int size);

    @Select("select * from product order by pdate desc limit ?, ?")
    List<Product> findNews(int isHot, int index, int size);

    @Select("select * from product where cid = ?")
    List<Product> findListByCid(String cid);
}
