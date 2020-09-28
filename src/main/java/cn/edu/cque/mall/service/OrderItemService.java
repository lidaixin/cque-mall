package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.OrderItem;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.utils.MapperUtil;
import cn.edu.cque.mall.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderItemService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 16:44
 * @Version 1.0
 **/
public class OrderItemService {

    public List<OrderItem> findListByOid(String oid) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
        List<OrderItem> all = mapper.findAllByOid(oid);
        sqlSession.close();
        return all;
    }

}
