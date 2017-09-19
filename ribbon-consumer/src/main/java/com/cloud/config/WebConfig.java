package com.cloud.config;

import com.cloud.filter.WebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by fengbin on 2017-07-31.
 */
@Configuration
public class WebConfig {
    /**
     * 增加过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("webFilter");
        return filterRegistrationBean;
    }

    /**
     * 实例化过滤器
     * @return
     */
    @Bean(name = "webFilter")
    public Filter getFilter(){
        return new WebFilter();
    }
}
