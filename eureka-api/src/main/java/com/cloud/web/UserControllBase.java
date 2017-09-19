package com.cloud.web;

import com.cloud.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fengbin on 2017-08-01.
 */
@RequestMapping(value = "/user")
interface UserControllBase {
    @RequestMapping(value = "/getUserName")
    public String getUserName(@RequestParam(value = "id") Long id);
    @RequestMapping(value = "/getUserById")
    public User getUserById(@RequestHeader(value = "id") Long id);
    @RequestMapping(value = "/getUserMsg")
    public String getUserMsg(@RequestBody User user);
}
