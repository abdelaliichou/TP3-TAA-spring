package com.example.springtp.repository;


import com.example.springtp.domain.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
    @Query(value = "SELECT * FROM provider", nativeQuery = true)
    public List<ProviderEntity> getAll();
}