package com.example.springtp.repository;

import com.example.springtp.domain.SQLQueries;
import com.example.springtp.domain.Participation;
import com.example.springtp.domain.Question;
import com.example.springtp.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{

    // TODO same db communication logic as the playerDao class

    @Query(SQLQueries.quizFindByAuthor)
    List<Quiz> findByAuteur(Long auteurId);

    @Query(SQLQueries.quizFindWithQuestions)
    List<Question> findQuestionsByQuiz(Long quizId);

    @Query(SQLQueries.participationFindByQuiz)
    List<Participation> findParticipationsByQuiz(Long quizId);

    @Modifying
    @Query(value = SQLQueries.creatQuiz, nativeQuery = true)
    Quiz createQuiz(Long authorId, String titre, String description);

    @Modifying
    @Query(value = SQLQueries.addQuestionToQuiz, nativeQuery = true)
    void addQuestionToQuiz(Long quizId, Question question);

    @Modifying
    @Query(value = SQLQueries.removeQuestionFromQuiz, nativeQuery = true)
    void removeQuestionFromQuiz(Long quizId, Long questionId);

    boolean existsByAuthorIdAndTitle(Long authorId, String titre);
}
