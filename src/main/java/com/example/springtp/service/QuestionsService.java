package com.example.springtp.service;

import com.example.springtp.domain.Question;
import com.example.springtp.domain.Response;
import com.example.springtp.dto.QuestionDto;
import com.example.springtp.mapper.QuestionMapper;
import com.example.springtp.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    // TODO same communication logic with the dao classes as we did the playerService class
    // TODO apply mapper from Question to QuestionDto as we did the playerService class

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionsService(QuestionRepository questionDao, QuestionMapper questionMapper) {
        this.questionRepository = questionDao;
        this.questionMapper = questionMapper;
    }

    public QuestionDto findOne(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.map(questionMapper::toDto).orElse(null);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public void save(Question entity) {
        if (entity == null) {
            return;
        }

        questionRepository.save(entity);
    }

    public void delete(Question entity) {
        if (entity == null) {
            return;
        }

        questionRepository.delete(entity);
    }

    public void deleteById(Long entityId) {
        if (entityId == null) {
            return;
        }

        questionRepository.deleteById(entityId);
    }

    public Question update(Question entity) {
        return null;
    }

    public List<Question> findByQuiz(Long quizId) {
        return List.of();
    }

    public List<Response> findResponsesByQuestion(Long questionId) {
        return List.of();
    }

    public void addResponseToQuestion(Long questionId, Response response) {}

    public void removeResponseFromQuestion(Long questionId, Long responseId) {}

    public boolean checkAnswer(Long questionId, Long responseId) {
        return false;
    }
}
