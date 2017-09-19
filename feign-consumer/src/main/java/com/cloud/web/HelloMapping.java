package com.cloud.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fengbin on 2017-08-01.
 */
@FeignClient(value = "eureka-client")
public interface HelloMapping {
    @GetMapping(value = "/sayHello")
    public String sayHello(@RequestParam(value = "msg") String msg);
}
