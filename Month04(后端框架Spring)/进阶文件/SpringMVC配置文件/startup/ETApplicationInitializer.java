package com.etoak.rst.startup;

import com.etoak.rst.config.ETConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * @Author Lushisan
 * @Date 2023/4/25 11:50
 * @Title: ETApplicationInitializer
 * @Description:
 * @Package com.etoak.emp.startup
 */
public class ETApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ETConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(ctx);
        ServletRegistration.Dynamic sd = servletContext.addServlet("servlet",servlet);
        sd.setLoadOnStartup(1);
        sd.addMapping("/");

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        FilterRegistration.Dynamic fd = servletContext.addFilter("encoding",filter);
        fd.addMappingForUrlPatterns(null,true,"/");
    }
}
