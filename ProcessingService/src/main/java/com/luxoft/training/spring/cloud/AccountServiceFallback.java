package com.luxoft.training.spring.cloud;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountServiceFallback implements AccountServiceClient {
    @Override
    public String checkout(int accountId, BigDecimal sum) {
        return null;
    }
}
