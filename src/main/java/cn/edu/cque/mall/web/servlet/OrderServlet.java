package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.Cart;
import cn.edu.cque.mall.entity.Order;
import cn.edu.cque.mall.entity.User;
import cn.edu.cque.mall.service.impl.OrderServiceImpl;

import java.util.List;

/**
 * @ClassName OrderServlet
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 11:42
 * @Version 1.0
 **/
public class OrderServlet extends BaseServlet {
    private OrderServiceImpl orderServiceImpl = new OrderServiceImpl();

    /***
     * 订单是由购物车转化来的
     * 购物车中的购物项可以直接转成订单项
     *
     **/
    @Path("create-order")
    public String createOrder() {
        // 1 获取参数
        User user = (User) request.getSession().getAttribute("loginUser");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 2 创建订单
        Order order = orderServiceImpl.createOrder(user, cart);
        // 3 将数据存入request域中跳转页面
        request.setAttribute("order", order);
        return "/WEB-INF/page/order-info";
    }

    @Path("update-order")
    public String updateOrder() {
        // 1 获取参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        // 2 更新订单
        orderServiceImpl.updateOrder(id, name, address, telephone);
        // 3 重定向到订单列表页面
        return "redirect:/order/list";
    }

    @Path("list")
    public String orderList() {
        // 1 从session中获取用户
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        // 1 查询订单列表
        List<Order> orderList = orderServiceImpl.findAllByUid(loginUser.getId());
        // 2 存储数据
        request.setAttribute("orderList", orderList);
        // 3 跳转页面
        return "/WEB-INF/page/order-list";
    }
}
