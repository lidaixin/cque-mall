package cn.edu.cque.mall.web.filter;

import cn.edu.cque.mall.entity.InfoResult;
import cn.edu.cque.mall.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginFilter
 * @Description 登录过滤器
 * @Author YoungWinter
 * @Date 2020/9/24 11:38
 * @Version 1.0
 **/
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1 强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 2 根据规则来进行拦截
        // 只拦截购物车和订单(通过配置完成)
        // 如果是AJAX的方式访问,会将页面作为数据直接返回
        // AJAX的方式会携带X-Requested-With: XMLHttpRequest请求头

        // 是否是AJAX请求的标志位
        boolean isAjax = false;
        String ajaxHeader = request.getHeader("X-Requested-With");
        if ((!StringUtils.isEmpty(ajaxHeader)) && ajaxHeader.equalsIgnoreCase("XMLHttpRequest")) {
            isAjax = true;
        }
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            // 未登录
            if (isAjax) {
                ObjectMapper objectMapper = new ObjectMapper();
                response.setContentType("application/json;charset=utf-8");
                objectMapper.writeValue(response.getWriter(), InfoResult.ERROR("用户未登录"));
            } else {
                response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
