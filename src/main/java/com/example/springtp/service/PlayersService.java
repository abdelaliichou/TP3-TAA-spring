package com.example.springtp.service;

import com.example.springtp.domain.Player;
import com.example.springtp.dto.ParticipationDto;
import com.example.springtp.dto.PlayerDto;
import com.example.springtp.dto.QuizDto;
import com.example.springtp.mapper.ParticipationMapper;
import com.example.springtp.mapper.PlayerMapper;
import com.example.springtp.mapper.QuizMapper;
import com.example.springtp.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayersService {

    // TODO create & add exceptions to every unwanted return

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final QuizMapper quizMapper;
    private final ParticipationMapper participationMapper;

    public PlayersService(
            PlayerRepository playerDao,
            QuizMapper quizMapper,
            PlayerMapper playerMapper,
            ParticipationMapper participationMapper
    ) {
        this.playerRepository = playerDao;
        this.playerMapper = playerMapper;
        this.quizMapper = quizMapper;
        this.participationMapper = participationMapper;
    }
    
    public PlayerDto findOne(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.map(playerMapper::toDto).orElse(null);
    }

    public List<PlayerDto> findAll() {
        return playerMapper.toDtoList(playerRepository.findAll());
    }

    public void save(PlayerDto entity) {
        if (entity == null) {
            return;
        }

        playerRepository.save(playerMapper.toEntity(entity));
    }

    public PlayerDto update(PlayerDto entity) {
        return null;
    }

    public void delete(PlayerDto entity) {
        if (entity == null) {
            return;
        }

        playerRepository.delete(playerMapper.toEntity(entity));
    }

    public void deleteById(Long entityId) {
        if (entityId == null) {
            return;
        }

        playerRepository.deleteById(entityId);
    }

    public PlayerDto findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }

        return playerMapper.toDto(playerRepository.findByEmail(email));
    }

    public List<QuizDto> findQuizByPlayer(Long playerId) {
        if (playerId == null) {
            return List.of();
        }

        return quizMapper.toDtoList(playerRepository.findQuizByPlayer(playerId));
    }

    public List<ParticipationDto> findParticipationsByPlayer(Long playerId) {
        if (playerId == null) {
            return List.of();
        }

        return participationMapper.toDtoList(playerRepository.findParticipationsByPlayer(playerId));
    }

    public boolean authenticate(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Login failed for email: " + email);
            return false;
        }

        return playerRepository.existsByEmail(email);
    }
}
