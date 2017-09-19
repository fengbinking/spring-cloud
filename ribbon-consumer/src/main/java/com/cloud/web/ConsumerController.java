package com.cloud.web;

import com.cloud.domain.User;
import com.cloud.service.HelloService;
import com.cloud.service.UserAnnoteCommand;
import com.cloud.service.UserGetCommand;
import com.cloud.service.UserPostCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengbin on 2017-07-21.
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    @Autowired
    private UserAnnoteCommand userAnnoteCommand;

    @RequestMapping("/ribbon-consumer")
    public String getRibbonConsumer() {
        return restTemplate.getForEntity("http://eureka-client/hello", String.class).getBody();
    }

    /**
     * RestTemplate get、post方法使用
     *
     * @return
     */
    @RequestMapping(value = "ribbon-all")
    public String ribbonAll() {
        StringBuffer buf = new StringBuffer();
        //占位符传参
        buf.append(restTemplate.getForEntity("http://eureka-client/sayHello?msg={1}",
                String.class, "get方式试用一下占位符传参").getBody());

        //Map传参
        Map<String, String> params = new HashMap<>();
        params.put("msg", "get方式试用下Map传参");
        buf.append("<br/> " + restTemplate.getForEntity("http://eureka-client/sayHello?msg={msg}",
                String.class, params).getBody());

        //post 传对象
        buf.append("<br/>" + restTemplate.postForEntity("http://eureka-client/sendObject?id={1}&userName={2}&age={3}",
                null, String.class, 20, "荆轲", 30).getBody());

        //post 传对象
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", "22");
        map.add("userName", "安琪拉拉");
        map.add("age", "98");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        buf.append("<br/>" + restTemplate.postForEntity("http://eureka-client/sendObject", request, String.class, 20, "荆轲", 30).getBody());

        //post 获取对象
        User userRes = restTemplate.postForEntity("http://eureka-client/getObject?id={1}",
                null, User.class, 1).getBody();
        buf.append("<br/> post 获取对象，用户名:" + userRes.getUserName() + ",年龄:" + userRes.getAge());

        return buf.toString();
    }

    @RequestMapping(value = "/hystrix")
    public String hystrix() {
        return helloService.sayHello();
    }

    /**
     * hystrix请求缓存
     *
     * @return
     */
    @GetMapping(value = "/hystrix_cache")
    public String hystrixCache(Long id) {
        User getUser = new UserGetCommand(restTemplate, id).execute();
        System.out.println("getUser==>>" + (getUser != null ? getUser.getUserName() : "not found"));
        /**二次相同请求，请求会被缓存*/
        getUser = new UserGetCommand(restTemplate, 4l).execute();
        System.out.println("getUser==>>" + (getUser != null ? getUser.getUserName() : "not found"));

        User user = new User(1, "a", 23);
        UserPostCommand userPostCommand = new UserPostCommand(restTemplate, user);
        User postUser = userPostCommand.execute();
        System.out.println("postUser==>>" + (getUser != null ? getUser.getUserName() : "not found"));

        getUser = new UserGetCommand(restTemplate, id).execute();
        System.out.println("getUser==>>" + (getUser != null ? getUser.getUserName() : "not found"));
        return "hystrix_cache request";
    }

    /**
     * 基于注解请求缓存
     *
     * @param id
     * @return
     */
    @GetMapping(value = "hystrixAnnoteCache")
    public String hystrixAnnoteCache(Long id) {
        return userAnnoteCommand.getUserById(id).getUserName()+"----"
                +userAnnoteCommand.getUserById(id).getUserName();
    }

    @GetMapping(value = "test")
    public String test(User user) {
        return user.getUserName() + "  " + user.getAge();
    }
}
