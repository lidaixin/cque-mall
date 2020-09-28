package cn.edu.cque.mall.mapper;

import cn.edu.cque.mall.common.Insert;
import cn.edu.cque.mall.common.Select;
import cn.edu.cque.mall.common.Update;
import cn.edu.cque.mall.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderMapper
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 10:39
 * @Version 1.0
 **/
public interface OrderMapper {

    //@Select("select * from orders where oid = ?")
    Order findById(@Param("id") String id);

    @Insert("insert into orders(oid,ordertime,total,state,uid) values(?,?,?,?,?)")
    int insert(String id, Date creatTime, double total, int state, String uid);

    @Update("update orders set name = ?, address = ?, telephone = ? where oid = ?")
    int updateOrder(String name, String address, String telephone, String id);

    @Select("select * from orders where uid = ?")
    List<Order> findAllByUid(String uid);

    void save(Order order);

    void updateOrderInfo(Order order);
}
