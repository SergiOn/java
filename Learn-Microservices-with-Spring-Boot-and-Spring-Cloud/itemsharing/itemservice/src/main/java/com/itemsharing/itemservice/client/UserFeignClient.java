package com.itemsharing.itemservice.client;

import com.itemsharing.itemservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("userservice")
public interface UserFeignClient {

    @RequestMapping(value = "/v1/user/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
    User getUserByUsername(@PathVariable("username") String username);

}
