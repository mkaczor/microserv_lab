package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRest {

    private AccountDAO accountDAO;
    private AccountRepository accountRepository;

    @Autowired
    public AccountRest(AccountDAO accountDAO, AccountRepository accountRepository) {
        this.accountDAO = accountDAO;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestParam  int clientId) {
        return ResponseEntity.ok(accountDAO.create(clientId));
    }

    @PutMapping("/{accountId}/fund")
    public ResponseEntity<Account> fund(@PathVariable int accountId, @RequestParam BigDecimal sum) {
        boolean added = accountDAO.addBalance(accountId, sum);
        if (!added) {
            return ResponseEntity.notFound().build();
        }

        Account one = accountRepository.findOne(accountId);
        return ResponseEntity.ok(one);
    }

    @PutMapping("/{accountId}/checkout")
    public ResponseEntity<Account> checkout(@PathVariable int accountId, @RequestParam BigDecimal sum) {
        return fund(accountId, sum.abs().negate());
    }

    @GetMapping("/get/{clientId}")
    public ResponseEntity<List<AccountEntity>> getClientAccount(@PathVariable int clientId) {
        return ResponseEntity.ok(accountRepository.findByClientId(clientId));
    }
}
