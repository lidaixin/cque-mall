package cn.edu.cque.mall.web.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName SpringContextListener
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/28 23:12
 * @Version 1.0
 **/
@Slf4j
public class SpringContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent ev) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ev.getServletContext().setAttribute("app", app);
        log.info("初始化SpringIOC容器");
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        ClassPathXmlApplicationContext app = (ClassPathXmlApplicationContext) ev.getServletContext().getAttribute("app");
        if (app != null) {
            app.close();
            log.info("销毁SpringIOC容器");
        }
    }
}
