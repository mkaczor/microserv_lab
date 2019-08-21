package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProcessingRest {

    private ProcessingRepository processingRepository;
    private CardServiceClient cardServiceClient;
    private AccountServiceClient accountServiceClient;

    @Autowired
    public ProcessingRest(ProcessingRepository processingRepository, CardServiceClient cardServiceClient, AccountServiceClient accountServiceClient) {
        this.processingRepository = processingRepository;
        this.cardServiceClient = cardServiceClient;
        this.accountServiceClient = accountServiceClient;
    }

    @PostMapping("/issue/{accountId}")
    public ResponseEntity<ProcessingEntity> issueAccount(@PathVariable int accountId) {
        String cardNumber = cardServiceClient.create();
        ProcessingEntity processingEntity = new ProcessingEntity();
        processingEntity.setAccountId(accountId);
        processingEntity.setCard(cardNumber);

        return ResponseEntity.ok(processingRepository.save(processingEntity));
    }

    @PutMapping("/checkout/{cardNumber}")
    public ResponseEntity<String> checkoutCard(@PathVariable String cardNumber, @RequestParam  BigDecimal sum) {
        ProcessingEntity byCard = processingRepository.findByCard(cardNumber);
        if (byCard == null) {
            return ResponseEntity.notFound().build();
        }

        String checkout = accountServiceClient.checkout(byCard.getAccountId(), sum);
        return ResponseEntity.ok(checkout);
    }

//    @GetMapping("/get")
//    public ResponseEntity<?> getCardsByAccount() {
//        processingRepository.f
//    }
}
