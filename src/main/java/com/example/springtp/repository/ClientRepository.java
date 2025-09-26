package com.example.springtp.repository;

import com.example.springtp.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Query(value = "SELECT * FROM client", nativeQuery = true)
    public List<ClientEntity> getAll();
}