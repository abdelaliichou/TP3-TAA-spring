package com.example.springtp.repository;

import com.example.springtp.domain.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    @Query(value = "SELECT * FROM store", nativeQuery = true)
    public List<StoreEntity> getAll();
}