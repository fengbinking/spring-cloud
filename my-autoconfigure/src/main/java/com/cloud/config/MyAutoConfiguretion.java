package com.cloud.config;

import com.cloud.service.MyAutoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fengbin on 2017-08-08.
 */
@Configuration
@EnableConfigurationProperties(value = MyAutoConfigProperties.class)
@ConditionalOnClass(value = MyAutoConfigService.class)
@ConditionalOnProperty(prefix = "myautoconfig",value = "enable",matchIfMissing = true)
public class MyAutoConfiguretion {
    @Autowired
    private MyAutoConfigProperties myAutoConfigProperties;
    @Bean
    public MyAutoConfigService getMyAutoConfigService(){
        MyAutoConfigService myAutoConfigService=new MyAutoConfigService();
        myAutoConfigService.setMsg(myAutoConfigProperties.getMsg());
        return myAutoConfigService;
    }
}
