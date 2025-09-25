package com.example.springtp.facade;

public interface IProvider {
    double getPrice(String articleRef);
    void order(String articleRef, int quantity);
}
