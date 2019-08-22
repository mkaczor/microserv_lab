package com.luxoft.training.spring.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(value = "AccountService", fallback = AccountServiceFallback.class)
public interface AccountServiceClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/account/{accountId}/checkout")
    String checkout(@PathVariable("accountId") int accountId, @RequestParam("sum") BigDecimal sum);
}
