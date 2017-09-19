package com.cloud.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by fengbin on 2017-08-03.
 */
//@Component
public class AccessRouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "route";
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
        System.out.println("*********AccessRouteFilter is run*********");
        return null;
    }
}
