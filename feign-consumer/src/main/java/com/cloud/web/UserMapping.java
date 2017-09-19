package com.cloud.web;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by fengbin on 2017-08-01.
 */
@FeignClient(value = "eureka-client")
public interface UserMapping extends UserControllBase{

}
