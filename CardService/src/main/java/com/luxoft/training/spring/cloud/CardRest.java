package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardRest {

    private CardNumberGenerator cardNumberGenerator;

    @Autowired
    public CardRest(CardNumberGenerator cardNumberGenerator) {
        this.cardNumberGenerator = cardNumberGenerator;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok(cardNumberGenerator.generate());
    }
}
