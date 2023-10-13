package com.example.accounts.dto;

import com.example.accounts.model.Account;

import java.util.List;

public class AccountDto {

    private String ownerName;

    private List<Account> accounts;

    private String balance;

    public AccountDto(String ownerName, List<Account> accounts, String balance) {
        this.ownerName = ownerName;
        this.accounts = accounts;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
