package com.example.springtp.repository;

import com.example.springtp.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    // TODO same db communication logic as the playerDao class

    public List<Response> findByQuestion(Long questionId);

    public List<Response> findCorrectResponsesByQuestion(Long questionId) ;
}
