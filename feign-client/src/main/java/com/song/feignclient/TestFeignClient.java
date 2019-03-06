package com.song.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: songcj
 * @Date: 2019/2/28 17:51
 * @Description:
 */
@FeignClient(name="microservice-songcj-feign-client")
public interface TestFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getUserName")
    String getUserName();

}
