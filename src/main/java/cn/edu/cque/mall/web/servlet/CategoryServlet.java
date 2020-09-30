package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.service.CategoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.List;

/**
 * @ClassName CategoryListServlet
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/22 15:50
 * @Version 1.0
 **/
public class CategoryServlet extends BaseServlet {



    // /category/list
    @Path("list")
    public List<Category> findAll() {
        CategoryService categoryService = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(CategoryService.class);
        return categoryService.findAll();
    }
}
