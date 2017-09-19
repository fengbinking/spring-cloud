package com.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengbin on 2017-08-03.
 */
@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
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
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        System.out.println("请求Url:"+request.getRequestURI());
        String accessToken=request.getParameter("accessToken");
        if(StringUtils.isEmpty(accessToken)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
        }
//        try {
//            this.doSomething();
//        } catch (Exception e) {
//            e.printStackTrace();
//            requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            requestContext.set("error.exception",e);
//        }
        System.out.println("*********AccessFilter is run*********");
        return null;
    }

    public void doSomething(){
        throw new RuntimeException("出错啦");
    }
}
