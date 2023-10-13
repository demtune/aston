package com.example.accounts.controller;

import com.example.accounts.model.Account;
import com.example.accounts.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/owner")
    private ResponseEntity<?> getAccountsByOwnerLogin(@RequestParam String login) {
        try {
            return ResponseEntity.ok().body(accountService.getByOwnerLogin(login));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(String login, Integer pinCode) {
        try {
            Account account = accountService.create(login, pinCode);
            return ResponseEntity.ok().body(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/fill")
    public ResponseEntity<?> fillBalanceAccount(String accountNumber, Double amount) {
        try {
            accountService.fillBalance(accountNumber, amount);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/charge")
    public ResponseEntity<?> chargeBalanceAccount(String accountNumber, Double amount, Integer pinCode) {
        try {
            accountService.chargeBalance(accountNumber, amount, pinCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferBalanceBetweenAccounts(String currentAccountNumber, String targetAccountNumber,  Double amount, Integer pinCode) {
        try {
            accountService.transferBalanceBetweenAccounts(currentAccountNumber, targetAccountNumber, amount, pinCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
