package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderMapper
 * @Description
 * @Author YoungWinter
 * @Date 2020/9/25 10:39
 * @Version 1.0
 **/
public interface OrderMapper {

    Order findById(@Param("id") String id);

    List<Order> findAllByUid(@Param("uid")String uid);

    void save(Order order);

    void updateOrderInfo(Order order);

    List<Order> findAll();

    @Delete("delete from orders where oid = #{oid}")
    int deleteById(@Param("oid") String oid);
}
