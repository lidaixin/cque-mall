package cn.edu.cque.mall.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName BaseServlet
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/23 11:47
 * @Version 1.0
 **/
public class BaseServlet extends HttpServlet {

    // 通过"内置对象"的思维来创建的两个可以在子类中直接使用的成员变量
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    // /product/findById?
    // /product/findByPage?
    // service方法写在哪里?因为这是公共方法,所有可以抽取到一个类中进行封装,让其他servlet去继承这个类
    // 如何在父类中获取并调用子类的方法? 反射技术
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // 每一次请求过来时都会被赋值
        this.request = req;
        this.response = resp;
        try {
            // 根据URI来找出对应的方法
            Method method = findMethod(request);
            // 如果没有找到就返回404
            if (method == null) {
                response.setStatus(404);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("<h3 style='margin-top: 200px;text-align: center; color: green'>没有匹配的方法</h3>");
                return;
            }
            // 调用对应的方法
            Object result = method.invoke(this);
            if (result instanceof String) {
                // 如果是字符串就跳转页面
                String path = (String) result;
                if (path.contains("WEB-INF")) {
                    request.getRequestDispatcher(result + ".jsp").forward(req, resp);
                } else if (path.contains("redirect:")) {
                    response.sendRedirect(request.getContextPath() + path.split(":")[1]);
                } else {
                    request.getRequestDispatcher("/jsp/" + result + ".jsp").forward(req, resp);
                }
            } else {
                // 如果是对象那么就转成json字符串响应给前端
                // 被转化的对象一定要有getter与setter
                ObjectMapper objectMapper = new ObjectMapper();
                response.setContentType("application/json;charset=utf-8");
                objectMapper.writeValue(response.getWriter(), result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * @Author YoungWinter
     * @Description 根据URI查询对应的方法, 被查找的方法要是public权限的
     * @Date 14:19 2020/9/23
     * @Param [req]
     * @return java.lang.reflect.Method
     **/
    private Method findMethod(HttpServletRequest req) {
        String uri = req.getRequestURI();
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Path annotation = method.getAnnotation(Path.class);
            if (annotation == null) continue;
            String path = method.getAnnotation(Path.class).value();
            if (uri.contains(path)) {
                return method;
            }
        }
        return null;
    }
}
