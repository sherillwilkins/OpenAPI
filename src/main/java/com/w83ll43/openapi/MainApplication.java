package com.w83ll43.openapi;

import com.w83ll43.openapi.filter.LoginCheckFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

//@EnableDubbo
// 过滤器扫描
@ServletComponentScan
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    /**
     * 通过 FilterRegistrationBean 注册过滤器
     * 函数名随意
     * 如果需要注册多个过滤器 则创建多个 Bean 即可
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        // 设置过滤器
        registration.setFilter(new LoginCheckFilter());

        // 设置过滤器名称
        registration.setName("loginCheckFilter");

        // 设置拦截路径
        registration.addUrlPatterns("/*");

        // 设置顺序（权重）使用这个方法可以配置过滤链
        registration.setOrder(10);
        return registration;
    }

}
