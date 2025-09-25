package com.example.springtp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String bankAccount;

    public ClientEntity() {}

    public ClientEntity(String name, String address, String bankAccount) {
        this.name = name;
        this.address = address;
        this.bankAccount = bankAccount;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getBankAccount() { return bankAccount; }

    public void setBankAccount(String bankAccount) { this.bankAccount = bankAccount; }
}

