package cn.edu.cque.mall.test.transaction;

import cn.edu.cque.mall.entity.Order;
import cn.edu.cque.mall.mapper.CategoryMapper;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.utils.DruidUtil;
import cn.edu.cque.mall.utils.MapperUtil;
import cn.edu.cque.mall.utils.UUIDUtils;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @ClassName TransactionTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 12:09
 * @Version 1.0
 **/
public class TransactionTest {

    @Test
    public void test01() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(DruidUtil.getDataSource());
        TransactionTemplate transactionTemplate = new TransactionTemplate(dataSourceTransactionManager);
        int i = transactionTemplate.execute(status -> {
            int result = 0;
            try {
                OrderItemMapper itemMapper = MapperUtil.getMapper(OrderItemMapper.class);
                String itemId = UUIDUtils.getId();
                itemMapper.insert(itemId, 0, 0, "1", "1");
                result++;
                itemMapper.insert(itemId, 0, 0, "1", "1");
                result++;
            } catch (Exception e) {
                e.printStackTrace();
                status.setRollbackOnly();
                result = 0;
            }
            return result;
        });
        System.out.println(i);
    }

    @Test
    public void test02(){
        OrderItemMapper itemMapper = MapperUtil.getMapper(OrderItemMapper.class);
        String itemId = UUIDUtils.getId();
        itemMapper.insert("1", 0, 0, "1", "1");
    }

    @Test
    public void test03(){
        CategoryMapper itemMapper = MapperUtil.getMapper(CategoryMapper.class);
        System.out.println(itemMapper.findById("1"));
    }
}
