package com.example.springtp.repository;

import com.example.springtp.domain.SQLQueries;
import com.example.springtp.domain.Participation;
import com.example.springtp.domain.Player;
import com.example.springtp.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(SQLQueries.playerFindByEmail)
    Player findByEmail(String email);

    @Query(SQLQueries.playerFindQuizByPlayer)
    List<Quiz> findQuizByPlayer(Long playerId) ;

    @Query(SQLQueries.playerFindParticipationsByPlayer)
    List<Participation> findParticipationsByPlayer(Long playerId);

    @Query(SQLQueries.playerAuthenticate)
    boolean existsByEmail(String email);

}
