package com.song.feignclient;

import org.springframework.beans.factory.annotation.Autowired;
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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TestFeignClient testFeignClient;

    @RequestMapping(method = RequestMethod.GET, value = "/getUserName")
    public String getUserName(){
        return "TestController";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserNameFromServer")
    public String getUserNameFromServer(){
        return testFeignClient.getUserName();
    }

}
