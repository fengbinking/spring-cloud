package com.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 通过HystrixCommand注解实现请求熔断
 * Created by fengbin on 2017-07-25.
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="error")
    public String sayHello(){
      return restTemplate.getForEntity("http://eureka-client/hello?name={1}",String.class,"ddd").getBody();
    }
    public String error(){
        return "Hystrix say timeout!";
    }
}
