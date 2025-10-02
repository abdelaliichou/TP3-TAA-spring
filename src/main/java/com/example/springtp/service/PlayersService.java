package com.example.springtp.service;

import com.example.springtp.domain.Player;
import com.example.springtp.domain.SQLQueries;
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

        if (entity == null) return;

        Player player = playerMapper.toEntity(entity);

        // Integrity 1: Email must be unique
        if (playerRepository.existsByEmail(player.getEmail())) {
            throw new IllegalStateException("Email already exists: " + player.getEmail());
        }

        // Integrity 2: Email must be valid format
        if (!player.getEmail().matches(SQLQueries.EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format: " + player.getEmail());
        }

        // Integrity 3: Role must be AUTHOR or PLAYER
        if (!SQLQueries.Roles.TEACHER.name().equals(player.getRole())
                && !SQLQueries.Roles.STUDENT.name().equals(player.getRole()))
        {
            throw new IllegalArgumentException("Invalid role: " + player.getRole());
        }

        // Integrity 4: Nickname must not be empty
        if (player.getName() == null || player.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be empty.");
        }

        playerRepository.save(player);
    }

    public PlayerDto update(PlayerDto entity) {

        if (entity == null) return null;

        Player player = playerMapper.toEntity(entity);

        // Integrity 1: Email must be unique
        if (playerRepository.existsByEmail(player.getEmail())) {
            throw new IllegalStateException("Email already exists: " + player.getEmail());
        }

        // Integrity 2: Email must be valid format
        if (!player.getEmail().matches(SQLQueries.EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format: " + player.getEmail());
        }

        // Integrity 3: Role must be AUTHOR or PLAYER
        if (!SQLQueries.Roles.TEACHER.name().equals(player.getRole())
                && !SQLQueries.Roles.STUDENT.name().equals(player.getRole()))
        {
            throw new IllegalArgumentException("Invalid role: " + player.getRole());
        }

        // Integrity 4: Nickname must not be empty
        if (player.getName() == null || player.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be empty.");
        }

        Player result = playerRepository.save(player);
        return playerMapper.toDto(result);
    }

    public void delete(PlayerDto entity) {
        if (entity == null) {
            return;
        }

        playerRepository.delete(playerMapper.toEntity(entity));
    }

    public void deleteById(Long entityId) {

        if (entityId == null) return;

        Player player = playerRepository.findById(entityId).orElseThrow(
                () -> new IllegalArgumentException("Player not found.")
        );

        // Integrity 5: Prevent deleting if still author of quizzes
        if (!playerRepository.findQuizByPlayer(entityId).isEmpty()) {
            throw new IllegalStateException("Cannot delete player: still author of quizzes.");
        }

        playerRepository.delete(player);
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
