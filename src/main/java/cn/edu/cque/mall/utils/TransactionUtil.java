package cn.edu.cque.mall.utils;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @ClassName TransactionUtil
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/25 16:01
 * @Version 1.0
 **/
public class TransactionUtil {

    private static TransactionTemplate txTemplate;
    static {
        // Spring的事务管理器
        DataSourceTransactionManager manager = new DataSourceTransactionManager(DruidUtil.getDataSource());
        // Spring的事务模板类对象
        txTemplate = new TransactionTemplate(manager);
    }

    public static TransactionTemplate getTxTemplate(){
        return txTemplate;
    }

}
