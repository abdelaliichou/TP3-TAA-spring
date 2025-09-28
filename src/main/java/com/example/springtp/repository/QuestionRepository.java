package com.example.springtp.repository;


import com.example.springtp.domain.Question;
import com.example.springtp.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // TODO same db communication logic as the playerDao class

    public List<Question> findByQuiz(Long quizId);

    public List<Response> findResponsesByQuestion(Long questionId);

    public void addResponseToQuestion(Long questionId, Response response);

    public void removeResponseFromQuestion(Long questionId, Long responseId) ;

    public boolean checkAnswer(Long questionId, Long responseId);
}
