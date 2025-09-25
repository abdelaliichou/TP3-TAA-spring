package com.example.springtp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class BankEntity {

    @Id
    private String accountNumber;
    private double balance;

    public BankEntity() {
    }

    public BankEntity(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
