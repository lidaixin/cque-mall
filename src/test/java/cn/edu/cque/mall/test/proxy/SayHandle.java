package cn.edu.cque.mall.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName SayHandle
 * @Description 针对实现了Sayable接口的类的对象的方法进行增强的处理器, 该处理器位于java.lang.reflect
 * @Author YoungWinter
 * @Date 2020/9/23 15:34
 * @Version 1.0
 **/
public class SayHandle implements InvocationHandler {

    private Object target;

    public SayHandle(Object target) {
        this.target = target;
    }

    /**
     * public void say() {
     *  System.out.println("增强小猫的say()");
     *  cat.say();
     * }
     * proxy表示的是代理的对象(CatProxy)
     * method表示被增强的方法(say())
     * args表示该方法的参数
     * Object表示增强后的返回值,通常与被增强的方法的返回值类型保持一致
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("增强小猫的say()");
        method.invoke(target);
        return null;
    }
}
