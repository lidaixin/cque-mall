package cn.edu.cque.mall.mapper;

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

}
