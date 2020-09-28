package cn.edu.cque.mall.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Path
 * @Description 通过注解的方式让方法告诉BaseServlet该方法要处理的请求的路径
 * @Author YoungWinter
 * @Date 2020/9/23 14:05
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dao {
}
