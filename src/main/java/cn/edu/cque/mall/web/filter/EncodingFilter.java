package cn.edu.cque.mall.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName EncodingFilter
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 11:33
 * @Version 1.0
 **/
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1 强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 2 处理编码
        if (request.getMethod().equalsIgnoreCase("post"))
            request.setCharacterEncoding("utf-8");
        // 3 放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
