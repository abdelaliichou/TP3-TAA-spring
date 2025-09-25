package com.example.springtp.dto;

public class ArticleDto {

    private String reference;
    private double price;
    private int stock;

    public ArticleDto() {
    }

    public ArticleDto(String reference, double price, int stock) {
        this.reference = reference;
        this.price = price;
        this.stock = stock;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}