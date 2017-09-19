package com.cloud.service;

import com.cloud.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *通过注解实现请求缓存
 * Created by fengbin on 2017-07-31.
 */
@Service
public class UserAnnoteCommand {
    @Autowired
    RestTemplate restTemplate;
    @CacheResult(cacheKeyMethod = "getUserByCacheKey")
    @HystrixCommand
    public User getUserById(Long id){
        return restTemplate.getForObject("http://eureka-client/getObject?id={1}", User.class, id);
    }
    public String getUserByCacheKey(Long id){
        return id.toString();
    }
}
