package com.cloud.service;

import com.cloud.domain.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * 通过继承HystrixCommand来实现请求缓存（get请求）
 * Created by fengbin on 2017-07-28.
 */
public class UserGetCommand extends HystrixCommand<User> {
    private static final HystrixCommandKey GETER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");
    private RestTemplate restTemplate;
    private Long id;

    public UserGetCommand(RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("getCommand")));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    @Override
    public User run() {
        System.out.println("UserGetCommand=>>"+id);
        return restTemplate.getForObject("http://eureka-client/findByUser?id={1}", User.class, id);
    }

    public static void flushCache(Long id) {
        HystrixRequestCache.getInstance(GETER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
