package com.example.accounts.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER", unique = true, nullable = false)
    private String number;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "PIN_CODE", nullable = false, length = 4) //ограничить 4 символами
    private Integer pinCode;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "RECORD_ID")
    private Record record;

    public Account() {
    }


    public Account(String number, BigDecimal balance, Integer pinCode, Owner owner, Record record) {
        this.number = number;
        this.balance = balance;
        this.pinCode = pinCode;
        this.owner = owner;
        this.record = record;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
