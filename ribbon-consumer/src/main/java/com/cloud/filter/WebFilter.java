package com.cloud.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by fengbin on 2017-07-31.
 */
public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context=null;
        try {
            //初始化HystrixContext
            context = HystrixRequestContext.initializeContext();
            filterChain.doFilter(request,response);
        } finally {
            if (context!=null)
                context.shutdown();
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
