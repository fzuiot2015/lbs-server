package edu.fzu.lbs.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private UserJwtInterceptor userJwtInterceptor;
    private AdminJwtInterceptor adminJwtInterceptor;

    @Autowired
    public InterceptorConfig(UserJwtInterceptor userJwtInterceptor, AdminJwtInterceptor adminJwtInterceptor) {
        this.userJwtInterceptor = userJwtInterceptor;
        this.adminJwtInterceptor = adminJwtInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO:设置拦截地址
        registry.addInterceptor(userJwtInterceptor).addPathPatterns("/personal/*");
//        registry.addInterceptor(adminJwtInterceptor).addPathPatterns("/");
    }

}
