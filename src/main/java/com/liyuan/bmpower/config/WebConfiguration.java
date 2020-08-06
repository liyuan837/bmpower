package com.liyuan.bmpower.config;

import com.liyuan.bmpower.filter.AccessFilter;
import com.liyuan.bmpower.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.Arrays;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Autowired
    private AccessFilter accessFilter;

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置拦截器白名单
        String[] excludes = new String[]{
                "/favicon**", "/error", "/*.html", "/swagger*", "/v2/*", "/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        };
        jwtInterceptor.setExcludes(Arrays.asList(excludes));
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**");
    }

    /**
     * 过滤处理跨域请求
     */
    @Bean
    public FilterRegistrationBean filterCORSRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setUrlPatterns(Arrays.asList(new String[]{"/*"}));
        registrationBean.setFilter(accessFilter);
        registrationBean.setOrder(3);
        return registrationBean;
    }
    
}
