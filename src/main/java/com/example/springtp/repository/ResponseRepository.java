package com.example.springtp.repository;

import com.example.springtp.domain.Response;
import com.example.springtp.domain.SQLQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    // TODO same db communication logic as the playerDao class

    @Query(SQLQueries.responsesFindWithQuestions)
    List<Response> findByQuestion(Long questionId);
}
