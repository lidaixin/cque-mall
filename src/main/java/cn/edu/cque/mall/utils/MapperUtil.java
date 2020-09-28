package cn.edu.cque.mall.utils;

import cn.edu.cque.mall.common.MapperHandler;
import cn.edu.cque.mall.mapper.CategoryMapper;

import java.lang.reflect.Proxy;

/**
 * @ClassName MapperUtil
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 16:29
 * @Version 1.0
 **/
public class MapperUtil {

    // 获取Mapper对应的代理对象
    // 通过JDK的代理模式产生了一个接口的匿名实现类的对象
    // 泛型方法
    public static <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new MapperHandler());
    }
}
