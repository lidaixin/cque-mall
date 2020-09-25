package cn.edu.cque.mall.web.servlet;

import cn.edu.cque.mall.common.BaseServlet;
import cn.edu.cque.mall.common.Path;
import cn.edu.cque.mall.entity.InfoResult;
import cn.edu.cque.mall.entity.User;
import cn.edu.cque.mall.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;

/**
 * @ClassName LoginServlet
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 9:48
 * @Version 1.0
 **/
public class UserServlet extends BaseServlet {

    // 1 在servlet中的成员不要作修改
    // 2 对于线程不安全问题可以通过事务来解决
    private UserService userService = new UserService();

    /**
     * @return cn.edu.cque.mall.entity.InfoResult
     * @Author YoungWinter
     * @Description 登录
     * @Date 11:48 2020/9/24
     * @Param []
     **/
    @Path("login")
    public InfoResult login() {
        // 1 获取数据
        String verCode = request.getParameter("verCode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 2 对验证码进行校验
        if (!CaptchaUtil.ver(verCode, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            return InfoResult.ERROR("验证码不正确");
        }
        CaptchaUtil.clear(request);
        // 3 登录
        User loginUser = userService.login(username, password);
        if (loginUser != null) {
            // 3.1 登录成功
            request.getSession().setAttribute("loginUser", loginUser);
            return InfoResult.OK();
        } else {
            // 3.2 登录失败
            return InfoResult.ERROR("用户名或者密码错误");
        }
    }

    @Path("logout")
    public InfoResult logout() {
        // 1 将用户信息从session中移除
        request.getSession().removeAttribute("loginUser");
        // 2 跳转页面
        return InfoResult.OK();
    }
}
