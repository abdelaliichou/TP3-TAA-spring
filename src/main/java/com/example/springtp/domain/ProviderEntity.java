package com.example.springtp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
public class ProviderEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String catalogue;

    public ProviderEntity() {}

    public ProviderEntity(String name, String catalogue) {
        this.name = name;
        this.catalogue = catalogue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
