package com.example.springtp.facade;

public interface IBank {
    boolean transfert(String fromAccount, String toAccount, double amount);
}
