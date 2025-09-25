package com.example.springtp.facade;

public interface ILane extends IStore {
    void addItemToCart(String articleRef, int quantity);
    void pay(String clientAccount, String clientAddress);
}
