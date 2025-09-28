package com.example.springtp.service;

import com.example.springtp.domain.Participation;
import com.example.springtp.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {

    // TODO same communication logic with the dao classes as we did the playerService class
    // TODO apply mapper from Question to QuestionDto as we did the playerService class

    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public List<Participation> findAll() {
        return participationRepository.findAll();
    }

    public void save(Participation entity) {
        if (entity == null) {
            return;
        }

        participationRepository.save(entity);
    }

    public Participation update(Participation entity) {
        return null;
    }

    public void delete(Participation entity) {
        if (entity == null) {
            return;
        }

        participationRepository.delete(entity);
    }

    public void deleteById(Long entityId) {
        if (entityId == null) {
            return;
        }

        participationRepository.deleteById(entityId);
    }

    public List<Participation> findByPlayer(Long playerId) {
        return participationRepository.findByPlayer(playerId);
    }

    public List<Participation> findByQuiz(Long quizId) {
        return participationRepository.findByQuiz(quizId);
    }

    public int calculateScore(Long participationId) {
        return participationRepository.calculateScore(participationId);
    }
}
