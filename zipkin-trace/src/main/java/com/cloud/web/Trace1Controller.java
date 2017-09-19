package com.cloud.web;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fengbin on 2017-08-04.
 */
@RestController
public class Trace1Controller {
    private final Logger log=Logger.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "receive1")
    public String receive1(@RequestParam(value = "msg")String msg){
        return restTemplate.postForEntity("http://ZIPKIN-TRACE2/trace2CheckMsg?msg={1}",
                null,String.class,msg).getBody();
    }
    @RequestMapping(value = "receive2")
    public String receive2(@RequestParam(value = "msg")String msg){
        return "接收到消息："+msg;
    }
    @GetMapping(value = "trace1")
    public String sayHelloTrace1(@RequestParam(value = "msg") String msg){
        log.info("======trace1======"+msg);
        return this.restTemplate.getForEntity("http://ZIPKIN-TRACE2/receive2?msg={1}",
                String.class,msg).getBody();
    }
    @RequestMapping(value = "trace2")
    public String sayHelloTrace2(@RequestParam(value = "msg")String msg){
        log.info("======trace2======"+msg);
        return restTemplate.postForEntity("http://ZIPKIN-TRACE1/receive1?msg={1}",
                null,String.class,msg).getBody();
    }
    @RequestMapping(value = "trace2CheckMsg")
    public String trace2CheckMsg(@RequestParam(value = "msg")String msg){
        if (StringUtils.isEmpty(msg))
            return "这是个空消息，你在逗我吗？";
        else if(msg.equals("sayHello"))
            return "消息审核通过";
        else
            return "非法的消息";

    }
}
