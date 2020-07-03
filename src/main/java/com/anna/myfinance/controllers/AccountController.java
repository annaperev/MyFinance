package com.anna.myfinance.controllers;

import com.anna.myfinance.dal.entities.Account;
import com.anna.myfinance.dal.repos.AccountRepo;
import com.anna.myfinance.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/accounts")
    public Account createAccount(/*@Valid*/ @RequestBody Account account) {//TODO
        return accountRepo.save(account);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Long accountId,
                                                   /*@Valid*/ @RequestBody Account accountDetails) throws ResourceNotFoundException {//TODO
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));

        account.setCurrency(accountDetails.getCurrency());
        account.setName(accountDetails.getName());
        account.setAmount(accountDetails.getAmount());
        account.setComment(accountDetails.getComment());
        final Account updatedAccount = accountRepo.save(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));

        accountRepo.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
