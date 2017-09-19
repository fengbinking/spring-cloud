package com.cloud.controller;

import com.cloud.domain.User;
import com.cloud.web.HelloMapping;
import com.cloud.web.UserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengbin on 2017-08-01.
 */
@RestController
public class ConsumerController {
    @Autowired
    private HelloMapping helloService;
    @Autowired
    private UserMapping userMapping;

    /**
     * 基于feign简单封装
     * @param msg
     * @return
     */
    @RequestMapping(value = "feign-consumer")
    public String feignConsumer(@RequestParam(value = "msg") String msg){
        return helloService.sayHello(msg);
    }

    /**
     * 基于feign加强版封装，代码更优雅
     * @param id
     * @return
     */
    @RequestMapping(value = "feignConsumerPlus")
    public String feignConsumerPlus(@RequestParam(value = "id",required = true) Long id){
        StringBuffer buf=new StringBuffer();
        buf.append("*****请求一*****"+userMapping.getUserById(id).getUserName());
        buf.append("<br/>*****请求二*****"+userMapping.getUserName(id));
        buf.append("<br/>*****请求三*****"+userMapping.getUserMsg(new User(id.intValue(),"name-UserMsg",99)));
        return buf.toString();
    }
}
