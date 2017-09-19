package com.cloud.web;

import com.cloud.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengbin on 2017-08-01.
 */
@RestController
public class FeignController implements UserControllBase{
    @Override
    public String getUserName(@RequestParam(value = "id") Long id) {
        return "测试-RequestParam,id:"+id;
    }

    @Override
    public User getUserById(@RequestHeader(value = "id") Long id) {
        return new User(id.intValue(),"测试-RequestHeader",13);
    }

    @Override
    public String getUserMsg(@RequestBody User user) {
        return "姓名:"+user.getUserName()+",id:"+user.getId();
    }
}
