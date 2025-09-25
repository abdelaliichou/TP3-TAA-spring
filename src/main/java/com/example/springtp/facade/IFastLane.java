package com.example.springtp.facade;

public interface IFastLane extends IStore {
    void oneShotOrder(String articleRef, int quantity, String clientAccount, String clientAddress);
}
