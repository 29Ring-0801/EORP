package pers.lintao.eorp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.lintao.eorp.interceptor.LoginInterceptor;

/**
 * @description: 配置类
 * @author: lilintao@163.com
 * @time: 2023/4/4 16时24分 周二
 */
@Configuration
public class EorpWebConfig implements WebMvcConfigurer {

    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/toLogin","/Kaptcha","/checkLogin","/static/**");
    }
}
