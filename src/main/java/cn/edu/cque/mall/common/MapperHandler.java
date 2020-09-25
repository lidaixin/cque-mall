package cn.edu.cque.mall.common;

import cn.edu.cque.mall.utils.DruidUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.ResultSet;
import java.util.List;

/**
 * @ClassName MapperHandler
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 16:12
 * @Version 1.0
 **/
public class MapperHandler implements InvocationHandler {

    private JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());

    /**
     * @Sql("select * from category where id = ?")
     * Category findById(String id);
     *
     * @Sql("select * from category")
     * List<Category> findAll();
     **/

    /**
     * @return java.lang.Object
     * @Author YoungWinter
     * @Description sql语句中的占位符必须与参数列表保持一致
     * @Date 16:14 2020/9/23
     * @Param [proxy, method, args]
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1 判断sql语句是select/update/insert/delete
        // annotation的类型是Annotation,Select.class的类型是Class
        Class annotation = method.getAnnotations()[0].annotationType();
        if (annotation.equals(Select.class)) {
            // 查询语句
            return SelectHandler(method, args);
        } else if (annotation.equals(Insert.class)) {
            // 插入语句
            return InsertHandler(method, args);
        } else if (annotation.equals(Update.class)) {
            // 插入语句
            return UpdateHandler(method, args);
        }

        return null;
    }

    /***
     * @Author YoungWinter
     * @Description 更新语句的处理器
     * @Date 16:08 2020/9/25
     * @Param [method, args]
     * @return java.lang.Object
     **/
    private Object UpdateHandler(Method method, Object[] args) {
        // 1 获取sql语句
        String sql = method.getAnnotation(Update.class).value();
        // 2 使用JdbcTemplate去执行sql并返回结果
        return template.update(sql, args);
    }


    /***
     * @Author YoungWinter
     * @Description 插入语句的处理器
     * @Date 11:39 2020/9/25
     * @Param [method, args]
     * @return java.lang.Object
     **/
    private Object InsertHandler(Method method, Object[] args) {
        // 1 获取sql语句
        String sql = method.getAnnotation(Insert.class).value();
        // 2 使用JdbcTemplate去执行sql并返回结果
        return template.update(sql, args);
    }

    /***
     * @Author YoungWinter
     * @Description 查询语句的处理器
     * @Date 11:39 2020/9/25
     * @Param [method, args]
     * @return java.lang.Object
     **/
    private Object SelectHandler(Method method, Object[] args) {
        // 1 获取sql语句
        String sql = method.getAnnotation(Select.class).value();
        // 2 获取返回值类型用于封装对象
        Class returnType = method.getReturnType();
        boolean isList = false;
        if (returnType.equals(List.class)) {
            // java.util.Map<cn.edu.cque.mall.entity.Category,String>
            isList = true;
            returnType = (Class) (((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0]);
        }
        // 3 使用JdbcTemplate去执行sql并返回结果
        List list = template.query(sql, new ProductRowMapper(returnType), args);
        // 4 根据返回值类型返回结果
        // 4.1 如果是List类型,直接返回
        if (isList)
            return list;
        // 4.2 如果不是List类型,返回list的第一个元素
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }


    // 将数据库中的数据封装成实体类的一个内部类
    private class ProductRowMapper<T> implements RowMapper<T> {

        public ProductRowMapper(Class<T> entityClass) {
            this.entityClass = entityClass;
        }

        // 实体类类型
        private Class<T> entityClass;

        @Override
        public T mapRow(ResultSet rs, int rowNum) {
            T t = null;
            try {
                t = entityClass.newInstance();
                // 封装对象
                // 找到属性的setter,然后将对应的列数据通过setter封装到实体类中
                Field[] fields = entityClass.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getAnnotation(Column.class) == null)
                        continue;
                    // name ---> setName()
                    Method method = entityClass.getDeclaredMethod(getSetter(field.getName()), field.getType());
                    method.invoke(t, rs.getObject(field.getAnnotation(Column.class).value()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return t;
        }
    }

    private String getSetter(String name) {
        String first = String.valueOf(name.charAt(0)).toUpperCase();
        String substring = name.substring(1);
        return "set" + first + substring;
    }
}
