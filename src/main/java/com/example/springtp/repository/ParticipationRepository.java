package com.example.springtp.repository;

import com.example.springtp.domain.Participation;
import com.example.springtp.domain.SQLQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    // TODO same db communication logic as the playerDao class

    @Query(SQLQueries.playerFindByID)
    List<Participation> findByPlayer(Long playerId);

    @Query(SQLQueries.participationFindByQuiz)
    List<Participation> findByQuiz(Long quizId) ;

    @Query(value = SQLQueries.calculateScore, nativeQuery = true)
    float calculateScore(Long participationId);
}
