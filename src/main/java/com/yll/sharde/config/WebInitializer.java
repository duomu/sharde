//package com.yll.sharde.config;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;
//
//import javax.servlet.*;
//import java.util.EnumSet;
//
///**
// * 替换web.xml
// * @author：linlin.yang
// * @date：2017/8/29 11:02
// */
//public class WebInitializer implements WebApplicationInitializer {
//    private static final String DISPATCHER_SERVLET_NAME = "springMvc";
//    private static final String DISPATCHER_SERVLET_MAPPING = "/";
//    private static final String CHARACTER_ENCODING_FILTER_NAME = "encodingFilter";
//    private static final String CHARACTER_ENCODING_FILTER_MAPPING = "/*";
//    private static final String URL_REWRITE_FILTER_NAME = "urlRewriteFilter";
//    private static final String URL_REWRITE_FILTER_MAPPING = "/*";
//    private static final String PATH_URL_MAPPING = "/";//匹配路径型url
//    private static final String ALL_URL_MAPPING = "/*";//匹配所有url
//
//    public void onStartup(ServletContext container) throws ServletException {
//        initContextLoaderListener(container);//启动Web容器时，自动装配Spring的配置信息
//        initDispatcherServlet(container);//springmvc前端控制器
//        initCharacterEncodingFilter(container);//字符编码过滤器
//        initUrlRewriteFilter(container);//伪静态过滤器
//    }
//
//    private void initContextLoaderListener(ServletContext container) {
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(Config.class);
//        container.addListener(new ContextLoaderListener(rootContext));
//    }
//
//    private void initDispatcherServlet(ServletContext container) {
//        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
//        mvcContext.register(SpringMvcConfig.class);
//        mvcContext.setServletContext(container);
//        ServletRegistration.Dynamic servlet = container.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(mvcContext));
//        servlet.addMapping(DISPATCHER_SERVLET_MAPPING);
//        servlet.setLoadOnStartup(1);
//        servlet.setAsyncSupported(true);
//
//    }
//
//    private void initCharacterEncodingFilter(ServletContext container) {
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        FilterRegistration.Dynamic filter = container.addFilter(CHARACTER_ENCODING_FILTER_NAME, new CharacterEncodingFilter("UTF-8"));
//        filter.addMappingForUrlPatterns(dispatcherTypes, true, CHARACTER_ENCODING_FILTER_MAPPING);
//
//    }
//
//    private void initUrlRewriteFilter(ServletContext container) {
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        FilterRegistration.Dynamic urlRewriteFilter = container.addFilter(URL_REWRITE_FILTER_NAME, new UrlRewriteFilter());
//        //配置文件默认路径：/WEB-INF/urlrewrite.xml
//        urlRewriteFilter.setInitParameter("confPath", "/WEB-INF/conf/urlrewrite.xml");
//        urlRewriteFilter.addMappingForUrlPatterns(dispatcherTypes, false, URL_REWRITE_FILTER_MAPPING);
//    }
//
//    private void initLog4jListener(ServletContext container) {
//
//
//    }
//
//}
