package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.entity.PageResult;
import cn.edu.cque.mall.entity.Product;
import cn.edu.cque.mall.service.impl.CategoryServiceImpl;
import cn.edu.cque.mall.service.ProductService;

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
        // 1 接受参数
        String id = request.getParameter("id");
        // 2 查询数据
        ProductService productService = new ProductService();
        Product product = productService.findById(id);
        // 3 存储数据
        request.setAttribute("product", product);
        return "detail";
    }

    @Path("/list")
    public String findListByCid(){
        // 1 接受参数
        String cid = request.getParameter("cid");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        // 2 查询数据
        ProductService productService = new ProductService();
        PageResult<Product> pageResult = productService.findPageResultByCid(cid, currentPage, pageSize);
        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
        Category category = categoryServiceImpl.findById(cid);
        // 3 将数据存到request域中并转发/jsp/list.jsp
        request.setAttribute("category", category);
        request.setAttribute("pageResult", pageResult);
        return "list";
    }
}
