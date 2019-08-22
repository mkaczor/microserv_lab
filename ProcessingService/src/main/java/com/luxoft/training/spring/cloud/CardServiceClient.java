package com.luxoft.training.spring.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "CardService", fallback = CardServiceFallback.class)
public interface CardServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/card/create")
    String create();
}
