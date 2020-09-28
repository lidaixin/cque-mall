package cn.edu.cque.mall.common;

import cn.edu.cque.mall.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName ServiceHandler
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 14:34
 * @Version 1.0
 **/
public class ServiceHandler implements InvocationHandler {

    // 实现类的对象
    private Object target;

    // 使用构造器初始化被代理对象
    public ServiceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1 根据外部信息获取SqlSession对象(是否批量处理)
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 2 通过SqlSession创建对应的Mapper
        // 方案一:通过args将Mapper信息传递进来
        // 方案二:通过target将Mapper信息传递进来
        // 方案三:通过target获取实现类的类型,通过反射获取成员变量,根据注解来获取对应的Mapper信息
        // 2.1 获取被代理对象的类型的所有成员变量
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 2.2 判断成员变量是否被@Dao所修饰
            Dao dao = field.getAnnotation(Dao.class);
            if (dao == null)
                continue;
            // 2.3 拿到对应的Mapper类型,通过SqlSession创建对应的Mapper
            Class type = field.getType();
            Object mapper = sqlSession.getMapper(type);
            // 2.4 将创建的Mapper设置到成员变量上
            field.setAccessible(true);
            field.set(target, mapper);
        }
        // 3 获取方法是否需要事务
        boolean isTx = false;
        Transaction annotation = target.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes()).getAnnotation(Transaction.class);
        if (annotation != null) {
            isTx = true;
            System.out.println("-----------开启事务-----------");
        }
        // 4 执行业务逻辑
        Object invoke = null;
        try {
            invoke = method.invoke(target, args);
            if (isTx) {
                sqlSession.commit();
                System.out.println("-----------提交事务-----------");
            }
        } catch (Exception e) {
            if (isTx) {
                sqlSession.rollback();
                System.out.println("-----------回滚事务-----------");
            }
        }
        // 5 关闭资源
        sqlSession.close();
        return invoke;
    }
}
