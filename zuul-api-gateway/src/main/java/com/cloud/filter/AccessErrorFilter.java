package com.cloud.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by fengbin on 2017-08-03.
 */
//@Component
public class AccessErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("*********AccessErrorFilter is run*********");
        return null;
    }
}
