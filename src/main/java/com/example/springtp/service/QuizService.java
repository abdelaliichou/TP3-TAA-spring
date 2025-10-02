package com.example.springtp.service;

import com.example.springtp.domain.Player;
import com.example.springtp.domain.Quiz;
import com.example.springtp.domain.SQLQueries;
import com.example.springtp.dto.ParticipationDto;
import com.example.springtp.dto.QuestionDto;
import com.example.springtp.dto.QuizDto;
import com.example.springtp.mapper.ParticipationMapper;
import com.example.springtp.mapper.QuestionMapper;
import com.example.springtp.mapper.QuizMapper;
import com.example.springtp.repository.PlayerRepository;
import com.example.springtp.repository.QuizRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService  {

    // TODO same communication logic with the dao classes as we did the playerService class

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final ParticipationMapper participationMapper;
    private final QuestionMapper questionMapper;
    private final PlayerRepository playerRepository;

    public QuizService(
            QuizRepository quizRepository,
            QuizMapper quizMapper,
            ParticipationMapper participationMapper,
            QuestionMapper questionMapper,
            PlayerRepository playerRepository) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.questionMapper = questionMapper;
        this.participationMapper = participationMapper;
        this.playerRepository = playerRepository;
    }

    public QuizDto findOne(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        return optionalQuiz.map(quizMapper::toDto).orElse(null);
    }

    public List<QuizDto> findAll() {
        return quizMapper.toDtoList(quizRepository.findAll());
    }

    public void save(QuizDto entity) {
        if (entity == null) {
            return;
        }

        quizRepository.save(quizMapper.toEntity(entity));
    }

    public void delete(QuizDto entity) {
        if (entity == null) {
            return;
        }

        quizRepository.delete(quizMapper.toEntity(entity));
    }

    public void deleteById(Long entityId) {
        if (entityId == null) {
            return;
        }

        quizRepository.deleteById(entityId);
    }

    public QuizDto update(QuizDto entity) {
        return null;
    }

    public List<QuizDto> findByAuteur(Long auteurId) {
        return quizMapper.toDtoList(quizRepository.findByAuteur(auteurId));
    }

    public List<QuestionDto> findQuestionsByQuiz(Long quizId) {
        return questionMapper.toDtoList(quizRepository.findQuestionsByQuiz(quizId));
    }

    public List<ParticipationDto> findParticipationsByQuiz(Long quizId) {
        return participationMapper.toDtoList(quizRepository.findParticipationsByQuiz(quizId));
    }

    public QuizDto createQuiz(Long authorId, String titre, String description) {
        Player author = playerRepository.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException("Author not found")
        );

        // Integrity rule 1: Only authors can create quizzes
        if (!author.getRole().equals(SQLQueries.Roles.TEACHER.name())) {
            throw new IllegalStateException("Only authors can create quizzes.");
        }

        // Integrity rule 2: Author cannot have duplicate quiz titles
        boolean exists = quizRepository.existsByAuthorIdAndTitre(authorId, titre);
        if (exists) {
            throw new IllegalStateException("This author already has a quiz with the same title.");
        }

        return quizMapper.toDto(quizRepository.createQuiz(
                    authorId,
                    titre,
                    description
            )
        );
    }

    public void addQuestionToQuiz(Long quizId, QuestionDto question) {
        quizRepository.addQuestionToQuiz(
                quizId,
                questionMapper.toEntity(question)
        );
    }

    public void removeQuestionFromQuiz(Long quizId, Long questionId) {
        quizRepository.removeQuestionFromQuiz(quizId, questionId);
    }
}
