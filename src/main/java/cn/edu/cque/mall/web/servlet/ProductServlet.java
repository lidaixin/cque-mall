package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.service.CategoryService;
import cn.edu.cque.mall.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * @ClassName ProductServlet
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 11:31
 * @Version 1.0
 **/
public class ProductServlet extends BaseServlet {

    // 可以通过注解的方式让方法告诉BaseServlet该方法要处理的请求的路径
    @Path("/detail")
    public String findById() {
        ProductService productService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ProductService.class);
        // 1 接受参数
        String id = request.getParameter("id");
        // 2 查询数据
        Product product = productService.findById(id);
        // 3 存储数据
        request.setAttribute("product", product);
        return "detail";
    }

    @Path("/list")
    public String findListByCid(){
        ProductService productService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(ProductService.class);
        CategoryService categoryService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CategoryService.class);
        // 1 接受参数
        String cid = request.getParameter("cid");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        // 2 查询数据
        PageResult<Product> pageResult = productService.findPageResultByCid(cid, currentPage, pageSize);
        Category category = categoryService.findById(cid);
        // 3 将数据存到request域中并转发/jsp/list.jsp
        request.setAttribute("category", category);
        request.setAttribute("pageResult", pageResult);
        return "list";
    }
}
