package com.example.springtp.facade;

public interface IJustHaveALook extends IStore {
    double getPrice(String articleRef);
    boolean isAvailable(String articleRef, int quantity);
}
