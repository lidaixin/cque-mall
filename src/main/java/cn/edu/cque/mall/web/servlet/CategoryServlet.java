package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.Category;
import cn.edu.cque.mall.service.CategoryService;
import org.springframework.context.ApplicationContext;

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

    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext app = (ApplicationContext) config.getServletContext().getAttribute("app");
        categoryService = app.getBean(CategoryService.class);
    }

    // /category/list
    @Path("list")
    public List<Category> findAll() {
        return categoryService.findAll();
    }
}
