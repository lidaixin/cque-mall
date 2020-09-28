package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.common.Insert;
import cn.edu.cque.mall.common.Select;
import cn.edu.cque.mall.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OrderItemMapper
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 10:39
 * @Version 1.0
 **/
public interface OrderItemMapper {

    void save(OrderItem orderItem);

    void saveBatch(@Param("orderItemList") List<OrderItem> orderItemList);

    List<OrderItem> findAllByOid(@Param("oid") String oid);

    //------------------------------------------------------------------------------
    @Insert("insert into orderitem values(?,?,?,?,?)")
    Integer insert(String id, int quantity, double total, String pid, String oid);

    @Select("select * from orderitem where oid = ?")
    List<OrderItem> findListByOid(String id);
}
