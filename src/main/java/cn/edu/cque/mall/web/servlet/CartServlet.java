package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.Cart;
import cn.edu.cque.mall.entity.CartItem;
import cn.edu.cque.mall.entity.InfoResult;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.service.CartService;
import cn.edu.cque.mall.service.CategoryService;
import cn.edu.cque.mall.service.ProductService;
import cn.edu.cque.mall.service.impl.ProductServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * @ClassName CartServlet
 * @Description 购物车Servlet
 * @Author YoungWinter
 * @Date 2020/9/24 11:41
 * @Version 1.0
 **/
public class CartServlet extends BaseServlet {

    @Path("list")
    public String cartList() {
        return "/WEB-INF/page/cart";
    }

    @Path("add-item")
    public InfoResult addItem() {
        ProductService productService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ProductService.class);
        CartService cartService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CartService.class);
        // 1 接受参数
        String pid = request.getParameter("pid");
        int num = Integer.parseInt(request.getParameter("num"));
        // 2 封装购物项
        Product product = productService.findById(pid);
        double sumPrice = product.getShopPrice() * num;
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setNum(num);
        cartItem.setSumPrice(sumPrice);
        // 3 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 4 添加购物项到购物车
        if (cart == null) {
            // 创建购物车
            cart = new Cart();
            cart = cartService.addItem(cart, cartItem);
        } else {
            cart = cartService.addItem(cart, cartItem);
        }
        request.getSession().setAttribute("cart", cart);
        return InfoResult.OK();
    }

    @Path("delete-item")
    public InfoResult deleteItem() {
        // 1 接受参数
        String pid = request.getParameter("pid");
        // 2 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 3 删除购物项
        cart.delCartItem(pid);
        // 4 判断是否还有商品
        if (cart.getList().size() == 0) {
            // 4.1 将购物车从session中移除
            request.getSession().removeAttribute("cart");
        } else {
            // 4.2 将购物车放入session中
            request.getSession().setAttribute("cart", cart);
        }
        return InfoResult.OK();
    }
}
