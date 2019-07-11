package com.site.mountain.listener;

import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

//@Service
public class InitDataListener implements InitializingBean, ServletContextAware {

    @Autowired
    SysUserService sysUserService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("afterPropertiesSet...22222222222222222222222.");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.print("setServletContext...111111111111111111111111111.");
        List list = sysUserService.findList(new SysUser());
        System.out.print("list==="+list.size());
    }
}
