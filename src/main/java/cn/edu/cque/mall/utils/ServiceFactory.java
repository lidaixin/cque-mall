package cn.edu.cque.mall.utils;

import cn.edu.cque.mall.common.ServiceHandler;

import java.lang.reflect.Proxy;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 15:17
 * @Version 1.0
 **/
public class ServiceFactory {

    public static <T> T getService(Class<T> clazz) {
        try {
            // 通过接口创建实现类的对象规则:
            // 1 实现类位于impl包中
            // 2 实现类的类名为接口名+Impl

            // cn.edu.cque.mall.service
            String className = clazz.getPackage().getName() + ".impl." + clazz.getSimpleName() + "Impl";
            Object o = Class.forName(className).newInstance();
            return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ServiceHandler(o));
        } catch (Exception e) {
            System.out.println("代理对象创建失败");
        }
        return null;
    }
}
