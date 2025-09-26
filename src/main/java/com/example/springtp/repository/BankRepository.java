package com.example.springtp.repository;

import com.example.springtp.domain.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, String> {
    @Query(value = "SELECT * FROM bank", nativeQuery = true)
    public List<BankEntity> getAll();
}