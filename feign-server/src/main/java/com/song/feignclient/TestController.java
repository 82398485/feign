package com.song.feignclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: songcj
 * @Date: 2019/2/28 17:14
 * @Description:
 */
@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/getUserName")
    public String getUserName(){
        return "TestController";
    }

}
