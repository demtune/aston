package com.example.accounts.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILL_BALANCE")
    private Date fillBalance;

    @Column(name = "CHARGE_BALANCE")
    private Date chargeBalance;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Record() {
    }

    public Record(Date fillBalance, Date chargeBalance, Date createDate) {
        this.fillBalance = fillBalance;
        this.chargeBalance = chargeBalance;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFillBalance() {
        return fillBalance;
    }

    public void setFillBalance(Date fillBalance) {
        this.fillBalance = fillBalance;
    }

    public Date getChargeBalance() {
        return chargeBalance;
    }

    public void setChargeBalance(Date chargeBalance) {
        this.chargeBalance = chargeBalance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
