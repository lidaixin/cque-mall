package cn.edu.cque.mall.test.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyTest
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 15:23
 * @Version 1.0
 **/
public class ProxyTest {

    @Test
    public void proxyTest01() {
        Cat cat = new Cat();
        Sayable sayable = new CatProxy(cat);
        sayable.say();
    }

    @Test
    public void proxyTest02() {
        Cat cat = new Cat();
        // 使用下面特定的方法创建cat的代理对象
        /**
         * ClassLoader loader,被代理对象所属的类的类加载器
         * Class<?>[] interfaces,被代理对象所属的类实现的接口
         * InvocationHandler h 增强的处理器,里面有对方法增强的代码
         **/
        Sayable catProxy = (Sayable) Proxy.newProxyInstance(cat.getClass().getClassLoader(),
                new Class[]{Sayable.class},
                new SayHandle(cat));
        catProxy.say();
    }
}
