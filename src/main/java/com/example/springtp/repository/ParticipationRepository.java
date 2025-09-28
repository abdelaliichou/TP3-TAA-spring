package com.example.springtp.repository;

import com.example.springtp.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    // TODO same db communication logic as the playerDao class

    public List<Participation> findByPlayer(Long playerId);

    public List<Participation> findByQuiz(Long quizId) ;

    public int calculateScore(Long participationId);
}
