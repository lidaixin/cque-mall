package cn.edu.cque.mall.test.proxy;

/**
 * @ClassName CatProxy
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 15:25
 * @Version 1.0
 **/
public class CatProxy implements Sayable {

    private Cat cat;

    public CatProxy(Cat cat) {
        this.cat = cat;
    }

    public void say() {
        System.out.println("增强小猫的say()");
        cat.say();
    }

}
