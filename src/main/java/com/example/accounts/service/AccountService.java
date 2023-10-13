package com.example.accounts.service;

import com.example.accounts.model.Account;
import com.example.accounts.model.Owner;
import com.example.accounts.model.Record;
import com.example.accounts.repository.AccountRepository;
import com.example.accounts.repository.OwnerRepository;
import com.example.accounts.repository.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.example.accounts.utils.AccountUtils.generateStringNumber;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final OwnerRepository ownerRepository;
    private final RecordService recordService;


    @Autowired
    public AccountService(AccountRepository accountRepository, OwnerRepository ownerRepository, RecordRepository recordRepository, RecordService recordService) {
        this.accountRepository = accountRepository;
        this.ownerRepository = ownerRepository;

        this.recordService = recordService;
    }


    @Transactional
    public Account create(String login, Integer pinCode) {
        Account account = new Account();
        Owner owner = ownerRepository.findByLogin(login);
        account.setNumber(generateStringNumber());
        account.setBalance(BigDecimal.valueOf(0.0));
        Record record = recordService.createRecordForNewAccount(account.getNumber());
        account.setPinCode(pinCode);
        account.setOwner(owner);
        account.setRecord(record);

        return accountRepository.save(account);
    }


    public List<Account> getByOwnerLogin(String login) {
        return accountRepository.findByOwnerLogin(login);
    }

    @Transactional
    public void fillBalance(String accountNumber, Double amount) {
        Account account = accountRepository.findByNumber(accountNumber);
        BigDecimal accountBalance = account.getBalance().add(BigDecimal.valueOf(amount));
        account.setBalance(accountBalance);
        accountRepository.save(account);
        recordService.createRecordForFillBalance(accountNumber);
    }

    @Transactional
    public void chargeBalance(String accountNumber, Double amount, Integer pinCode) {
        Account account = accountRepository.findByNumberAndPinCode(accountNumber, pinCode);
        BigDecimal accountBalance = account.getBalance().subtract(BigDecimal.valueOf(amount));
        account.setBalance(accountBalance);
        accountRepository.save(account);
        recordService.createRecordForChargeBalance(accountNumber);
    }

    @Transactional
    public void transferBalanceBetweenAccounts(String currentAccountNumber, String targetAccountNumber,  Double amount, Integer currentAccountPinCode) {
        Account currentAccount = accountRepository.findByNumberAndPinCode(currentAccountNumber, currentAccountPinCode);
        Account targetAccount = accountRepository.findByNumber(targetAccountNumber);

        BigDecimal currentAccountBalance = currentAccount.getBalance().subtract(BigDecimal.valueOf(amount));
        BigDecimal targetAccountBalance = targetAccount.getBalance().add(BigDecimal.valueOf(amount));

        currentAccount.setBalance(currentAccountBalance);
        accountRepository.save(currentAccount);
        targetAccount.setBalance(targetAccountBalance);
        accountRepository.save(targetAccount);

        recordService.createRecordForChargeBalance(currentAccountNumber);
        recordService.createRecordForFillBalance(targetAccountNumber);
    }
}
