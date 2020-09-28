package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.service.CategoryService;
import cn.edu.cque.mall.service.ProductService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName IndexServlet
 * @Description 首页Servlet 封装首页需要的数据,然后转发到/jsp/index.jsp
 * @Author YoungWinter
 * @Date 2020/9/22 10:34
 * @Version 1.0
 **/
public class IndexServlet extends HttpServlet {

    private CategoryService categoryService;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext app = (ApplicationContext) config.getServletContext().getAttribute("app");
        categoryService = app.getBean(CategoryService.class);
        productService = app.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 查询数据
        // 1.1 查询商品类别数据
        List<Category> categoryList = categoryService.findAll();
        // 1.2 查询最新商品数据
        List<Product> hotList =  productService.findHot();
        List<Product> newsList =  productService.findNews();
        // 1.3 查询最热商品数据
        // 2 将数据存入request域中
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("hotList", hotList);
        req.setAttribute("newsList", newsList);
        // 3 转发到/jsp/index.jsp
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }
}
