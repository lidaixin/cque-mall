package cn.edu.cque.mall.test.mybatis;

import cn.edu.cque.mall.entity.*;
import cn.edu.cque.mall.mapper.CategoryMapper;
import cn.edu.cque.mall.mapper.OrderItemMapper;
import cn.edu.cque.mall.mapper.OrderMapper;
import cn.edu.cque.mall.mapper.ProductMapper;
import cn.edu.cque.mall.service.impl.OrderServiceImpl;
import cn.edu.cque.mall.utils.SqlSessionUtil;
import cn.edu.cque.mall.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MybatisTest02 {

    private SqlSession sqlSession;

    @Before
    public void beforeTest() {
        sqlSession = SqlSessionUtil.getSqlSession();
    }

    @After
    public void afterTest() {
        if (sqlSession != null)
            sqlSession.close();
    }

    @Test
    public void test01() throws Exception {
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        Category category = mapper.findById("1");
        System.out.println(category);
    }

    @Test
    public void test02() throws Exception {
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        List<Category> categoryList = mapper.findAll();
        categoryList.forEach(System.out::println);
    }

    @Test
    public void test03() throws Exception {
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        Product product = mapper.findById("1");
        System.out.println(product);
    }

    @Test
    public void test04() throws Exception {
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        // 分页步骤
        // 1 开始分页
        // int pageNum 当前页
        // int pageSize 每页显示条数
        PageHelper.startPage(1, 5);
        // 2 调用mapper方法进行查询
        List<Product> productList = mapper.findAllByCid("1");
        // 3 将结果封装成分页信息对象
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        pageInfo.getList().forEach(System.out::println);
        System.out.println("总页数:" + pageInfo.getPages());
        System.out.println("总记录数:" + pageInfo.getTotal());
    }

    @Test
    public void test05() {
        // 发送的是DML语句,检查一下事务的提交方式
        OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
        OrderItem orderItem = OrderItem.builder().id(UUIDUtils.getId().toLowerCase().substring(0, 5))
                .oid("61FD03EFF14043039D29C1A62F48AC07")
                .pid("10")
                .quantity(5)
                .total(3000.00)
                .build();
        try {
            mapper.save(orderItem);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }

    }

    @Test
    public void test06() {
        OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
        List<OrderItem> itemList = mapper.findAllByOid("C400C3A75FB446F8A5CF347CBB8D13DF");
        System.out.println(itemList.get(0).getProduct());
        double total = itemList.stream().mapToDouble(i -> i.getTotal()).reduce((a, b) -> a + b).getAsDouble();
        System.out.println(total);
    }

    /***
     * insert into orderitem(itemid, quantity, total, pid, oid) values(?, ?, ?, ?, ?),(?, ?, ?, ?, ?)
     **/
    @Test
    public void test07() {
        // 1 准备数据
        User user = new User();
        user.setId("3");
        Cart cart = new Cart();
        cart.setTotalPrice(350000.00);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        //---------------------------------------------------------
        Product product = productMapper.findById("3");
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setNum(3);
        cartItem.setSumPrice(product.getShopPrice() * 3);
        cart.addCartItem(cartItem);

        Product product1 = productMapper.findById("1");
        CartItem cartItem1 = new CartItem();
        cartItem1.setProduct(product1);
        cartItem1.setNum(2);
        cartItem1.setSumPrice(product1.getShopPrice() * 2);
        cart.addCartItem(cartItem1);
        //-------------------------------------------------------------
        OrderServiceImpl service = new OrderServiceImpl();
        Order order = service.saveOrder(user, cart);
        System.out.println(order);
    }

    @Test
    public void test08() {
        OrderServiceImpl service = new OrderServiceImpl();
        Order order = service.findById("5A9C0EA4DBBB4722BBFA64A0BBFB50C4");
        System.out.println(order);
    }

    @Test
    public void test09() {
        OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
        OrderItem o1 = OrderItem.builder().oid("809A1A6E7D864845B2AAFFD674632E88").pid("1").id("234").build();
        OrderItem o2 = OrderItem.builder().oid("809A1A6E7D864845B2AAFFD674632E88").pid("2").id("2345").build();
        List<OrderItem> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        // insert into orderitem(itemid, quantity, total, pid, oid) values (?, ?, ?, ?, ?) , (?, ?, ?, ?, ?)
        mapper.saveBatch(list);
        sqlSession.commit();
    }

    @Test
    public void test10() {
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = Order.builder().id("3B1A8CBBB4314CBBB66A907DD7BD3EE2")
                //.name("刘德华")
                .address("重庆市南岸区学府大道520号")
                .telephone("15523458907")
                .build();
        mapper.updateOrderInfo(order);
        sqlSession.commit();
    }


}
