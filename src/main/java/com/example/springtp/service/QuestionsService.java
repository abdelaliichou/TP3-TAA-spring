package com.example.springtp.service;

import com.example.springtp.domain.Question;
import com.example.springtp.dto.QuestionDto;
import com.example.springtp.dto.ResponseDto;
import com.example.springtp.mapper.QuestionMapper;
import com.example.springtp.mapper.ResponseMapper;
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
    private final ResponseMapper responseMapper;

    public QuestionsService(QuestionRepository questionDao, QuestionMapper questionMapper, ResponseMapper responseMapper) {
        this.questionRepository = questionDao;
        this.questionMapper = questionMapper;
        this.responseMapper = responseMapper;
    }

    public QuestionDto findOne(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.map(questionMapper::toDto).orElse(null);
    }

    public List<QuestionDto> findAll() {
        return questionMapper.toDtoList(questionRepository.findAll());
    }

    public void save(QuestionDto entity) {
        if (entity == null) {
            return;
        }

        questionRepository.save(questionMapper.toEntity(entity));
    }

    public void delete(QuestionDto entity) {
        if (entity == null) {
            return;
        }

        questionRepository.delete(questionMapper.toEntity(entity));
    }

    public void deleteById(Long entityId) {
        if (entityId == null) {
            return;
        }

        questionRepository.deleteById(entityId);
    }

    public QuestionDto update(QuestionDto entity) {
        return null;
    }

    public List<QuestionDto> findByQuiz(Long quizId) {
        return questionMapper.toDtoList(questionRepository.findByQuiz(quizId));
    }

    public List<ResponseDto> findResponsesByQuestion(Long questionId) {
        return responseMapper.toDtoList(questionRepository.findResponsesByQuestion(questionId));
    }

    public void addResponseToQuestion(Long questionId, ResponseDto response) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new RuntimeException("Question not found")
        );

        question.getReponses().add(responseMapper.toEntity(response));
        questionRepository.save(question);
    }

    public void removeResponseFromQuestion(Long questionId, Long responseId) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new RuntimeException("Question not found")
        );

        // Remove the response by its ID
        question.getReponses().removeIf(r -> r.getId().equals(responseId));
        questionRepository.save(question);
    }

    public boolean checkAnswer(Long questionId, Long responseId) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new RuntimeException("Question not found")
        );
        return question.getReponses().stream().anyMatch(r ->
                r.getId().equals(responseId) && Boolean.TRUE.equals(r.getIsCorrect())
        );
    }
}
