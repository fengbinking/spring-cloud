package com.cloud.service;


import com.cloud.domain.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 通过继承HystrixCommand来实现请求缓存（post请求）
 * Created by fengbin on 2017-07-28.
 */
public class UserPostCommand extends HystrixCommand<User>{
    RestTemplate restTemplate;
    User user;
    public UserPostCommand(RestTemplate restTemplate,User user){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("getCommand")));
        this.restTemplate=restTemplate;
        this.user=user;
    }
    @Override
    public User run() throws Exception {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", "22");
        map.add("userName", "安琪拉拉");
        map.add("age", "98");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        User r=restTemplate.postForEntity("http://eureka-client/saveUser",request,User.class).getBody();
        UserGetCommand.flushCache(Integer.valueOf(user.getId()).longValue());
        return r;
    }
}
