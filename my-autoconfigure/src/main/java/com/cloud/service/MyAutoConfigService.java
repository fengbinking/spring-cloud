package com.cloud.service;

/**
 * Created by fengbin on 2017-08-08.
 */
public class MyAutoConfigService {
    private String msg;
    public String sayHello(){
        return ">>>>>>>"+msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
