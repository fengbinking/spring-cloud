package com.cloud.web;

import com.cloud.domain.User;
import com.cloud.service.MyAutoConfigService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fengbin on 2017-07-21.
 */
@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;
    private User userCache;
    @Autowired
    private MyAutoConfigService myAutoConfigService;
    private Logger log=Logger.getLogger(getClass());
    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<ServiceInstance> list = discoveryClient.getInstances("STORES");
        System.out.println("getLocalServiceInstance="+discoveryClient.getLocalServiceInstance());
        System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());
        for( String s :  discoveryClient.getServices()){
            System.out.println("services " + s);
            List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
            for(ServiceInstance si : serviceInstances){
                System.out.println("    services:" + s + ":getHost()=" + si.getHost());
                System.out.println("    services:" + s + ":getPort()=" + si.getPort());
                System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
                System.out.println("    services:" + s + ":getUri()=" + si.getUri());
                System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
            }

        }

        System.out.println(list.size());
        if (list != null && list.size() > 0 ) {
            System.out.println( list.get(0).getUri()  );
        }
        return discoveryClient.getLocalServiceInstance().getHost()+" request success";
    }

    @RequestMapping(value = "/sayHello")
    public String sayHello(@RequestParam(value = "msg") String msg){
        System.out.println("sayHello>> ribbon tell:"+msg);
        return "消息发送成功!";
    }
    @RequestMapping(value = "/sendObject")
    public String sendObject(User user){
        System.out.println("sendObject>> 获取到对象,用户名："+user.getUserName());
        return "姓名："+user.getUserName()+"\t 年龄："+user.getAge();
    }
    @RequestMapping(value = "getObject")
    public User getObject(@RequestParam(value = "id") int id){
        System.out.println("getObject>> id:"+id);
        if(id==1)
            return new User(id,"王麻子",30);
        else if (id==2)
            return new User(id,"小李子",12);
        else
            return new User(0,"无名土",99);
    }
    @PostMapping(value = "/saveUser")
    public User saveUser(User user){
        System.out.println("request send saveUser["+user.getId()+" "+user.getUserName()+"]....");
        userCache=user;
        return userCache;
    }
    @GetMapping(value = "findByUser")
    public User findByUser(Long id){
        System.out.println("request send findByUser["+id+"]");
        if (userCache!=null&&userCache.getId()==id.intValue())
            return userCache;
        else
            return null;
    }

    @RequestMapping(value = "myautoconfig")
    public String sayAutoConfig(){
        String result=myAutoConfigService.sayHello();
        log.info("*********myautoconfig:"+result);
        return result;
    }
}
