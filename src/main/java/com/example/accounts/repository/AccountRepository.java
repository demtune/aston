package com.example.accounts.repository;

import com.example.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwnerLogin(String login);
    Account findByNumber(String accountNumber);
    Account findByNumberAndPinCode(String accountNumber, Integer pinCode);
}
