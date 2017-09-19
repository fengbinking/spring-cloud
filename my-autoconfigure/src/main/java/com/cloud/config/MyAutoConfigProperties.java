package com.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fengbin on 2017-08-08.
 */
@ConfigurationProperties(prefix = "myautoconfig")
public class MyAutoConfigProperties {
   private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
