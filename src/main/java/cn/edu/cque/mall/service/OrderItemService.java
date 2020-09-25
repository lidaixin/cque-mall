package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.OrderItem;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.utils.MapperUtil;

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

    private OrderItemMapper mapper = MapperUtil.getMapper(OrderItemMapper.class);

    private ProductMapper productMapper = MapperUtil.getMapper(ProductMapper.class);

    public List<OrderItem> findListByOid(String oid) {
        return mapper.findListByOid(oid).stream().map(i -> {
            Product product = productMapper.findById(i.getPid());
            i.setProduct(product);
            return i;
        }).collect(Collectors.toList());
    }

}
