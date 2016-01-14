package com.lang71.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class Initializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    public void onStartup(ServletContext servletContext)
            throws ServletException {

        LOGGER.info("start initialization");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/*");
        servlet.setLoadOnStartup(1);

        LOGGER.info("initialization has been completed");
    }

}
