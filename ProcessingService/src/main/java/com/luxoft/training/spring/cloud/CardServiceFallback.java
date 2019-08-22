package com.luxoft.training.spring.cloud;

import org.springframework.stereotype.Component;

@Component
public class CardServiceFallback implements CardServiceClient {

    @Override
    public String create() {
        return null;
    }
}
