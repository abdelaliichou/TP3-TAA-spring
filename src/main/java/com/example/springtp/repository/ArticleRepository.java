package com.example.springtp.repository;

import com.example.springtp.domain.ArticleEntity;
import com.example.springtp.domain.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    ArticleEntity findByReference(String reference);
}