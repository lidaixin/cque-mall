package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.*;
import cn.edu.cque.mall.service.CategoryService;
import cn.edu.cque.mall.service.OrderService;
import cn.edu.cque.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.List;

public class AdminServlet extends BaseServlet {
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private ProductService productService;

    //商品分类列表
    @Path("/cart/list")
    public String cartList(){
        //查询所有的商品分类
        CategoryService categoryService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CategoryService.class);
        List list = categoryService.findAll();
//        System.out.println(list);
        request.setAttribute("carts",list);
        return "category-list";
    }

    //商品列表
    @Path("/product/list")
    public String productList(){
        ProductService productService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ProductService.class);
        //获取参数
        String cid = request.getParameter("cid");
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //分页查询全部商品
        PageResult<Product> result = productService.findPageResultByCid(cid, currentPage, pageSize);
        System.out.println(result);


        List<Product> products = productService.findPageAll();
        System.out.println(products);
        request.setAttribute("pageResult",result);
        request.setAttribute("products",products);
        return "product-list";
    }

    //订单列表
    @Path("/order/list")
    public String orderList(){
        OrderService orderService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean( OrderService.class);
        //分页查询全部订单
        List<Order> orders = orderService.findAll();
        request.setAttribute("orders",orders);
        return "order-list";
    }

    //添加商品分类
    @Path("/addCategory")
    public String addCategory(){
        //获取表单数据
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        Category category = new Category(categoryId,categoryName);
        //封装表单数据
//        category.setCname();
        CategoryService categoryService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CategoryService.class);
        categoryService.save(category);

        List list = categoryService.findAll();
//        System.out.println(list);
        request.setAttribute("carts",list);
        return "category-list";
    }

    //修改商品分类
    @Path("/updateCategory")
    public String updateCategoryByCid(){
        CategoryService categoryService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CategoryService.class);
        //获取参数
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
//        System.out.println(cid+":"+cname);
        Category category = new Category(cid,cname);
        categoryService.updateByCid(category);
        List list = categoryService.findAll();
//        System.out.println(list);
        request.setAttribute("carts",list);
        return "category-list";
    }

    //删除商品分类
    @Path("/deleteCategory")
    public InfoResult deleteCategory(){
        String cid = request.getParameter("cid");
        CategoryService categoryService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CategoryService.class);
        categoryService.deleteByCid(cid);
        return InfoResult.OK();
    }

    //添加订单
    @Path("/addOrder")
    public String addOrder(){
        return "order-list";
    }

    //修改订单
    @Path("/updateOrder")
    public String updateOrder(){
        return "order-list";
    }

    //删除订单  （有问题：Uncaught SyntaxError: Invalid or unexpected token）
    @Path("/deleteOrder")
    public InfoResult deleteOrder(){
        OrderService orderService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(OrderService.class);
        //获取id
        String oid = request.getParameter("oid");
        //根据oid删除订单
        orderService.deleteById(oid);
        return InfoResult.OK();
    }

    //添加商品
    @Path("/addProduct")
    public String addProduct(){
        ProductService productService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ProductService.class);
        //获取商品信息
        String name = request.getParameter("name");
        Double marketPrice = Double.parseDouble(request.getParameter("marketPrice"));
        Double shopPrice = Double.parseDouble(request.getParameter("shopPrice"));
        String pImage = request.getParameter("pImage");
        Integer isHot = Integer.parseInt(request.getParameter("isHot"));
        String pdesc = request.getParameter("pdesc");
        //封装商品信息
        Product product = new Product();
        product.setDesc(pdesc);
        product.setImage(pImage);
        product.setMarketPrice(marketPrice);
        product.setIsHot(isHot);
        product.setName(name);
        product.setShopPrice(shopPrice);
        //添加商品
        productService.addProduct(product);
        List<Product> products = productService.findPageAll();
//        System.out.println(products);
        request.setAttribute("products",products);
        return "product-list";
    }

    //修改商品信息
    @Path("/updateProduct")
    public String updateProduct(){
        return "product-list";
    }

    //删除商品（假删除 将isFlag置为1）
    @Path("/deleteProduct")
    public InfoResult deleteProduct(){
        ProductService productService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ProductService.class);
        //获取商品Id
        String id = request.getParameter("pid");
        //根据id删除商品
        productService.deleteById(id);
        return InfoResult.OK();
    }

}
