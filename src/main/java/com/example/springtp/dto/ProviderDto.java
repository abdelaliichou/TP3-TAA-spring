package com.example.springtp.dto;

public class ProviderDto {
    private String name;
    private String catalogue;

    public ProviderDto() {}

    public ProviderDto(String name, String catalogue) {
        this.name = name;
        this.catalogue = catalogue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }
}