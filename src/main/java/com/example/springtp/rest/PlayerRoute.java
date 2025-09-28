package com.example.springtp.rest;

import com.example.springtp.dto.ParticipationDto;
import com.example.springtp.dto.PlayerDto;
import com.example.springtp.dto.QuizDto;
import com.example.springtp.service.PlayersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Players", description = "Endpoints for managing players")
public class PlayerRoute {

    PlayersService playersService;

    public PlayerRoute(PlayersService playersService) {
        this.playersService = playersService ;
    }

    @GetMapping
    @Operation(
            summary = "Get all players",
            description = "Returns a list of all players",
            responses = {
                    @ApiResponse(
                            description = "List of players",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PlayerDto.class
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return ResponseEntity.ok(playersService.findAll());
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Get player by email",
            description = "Fetches a player using their email address",
            responses = {
                    @ApiResponse(
                            description = "The player",
                            content = @Content(schema = @Schema(implementation = PlayerDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Player not found"
                    )
            }
    )
    public ResponseEntity<?> getPlayerByEmail(
            @Parameter(
                    description = "Email of the player",
                    required = true
            )
            @PathVariable("email") String email
    ) {
        PlayerDto player = playersService.findByEmail(email);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Player not found with email: " + email);
        }

        return ResponseEntity.ok(player);
    }

    @PostMapping("/auth")
    @Operation(
            summary = "Authenticate a player",
            description = "Authenticate a player using email",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authenticated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication failed"
                    )
            }
    )
    public ResponseEntity<?> authenticate(
            @Parameter(
                    description = "Email of the player to authenticate",
                    required = true
            )
            @RequestParam("email") String email
    ) {
        boolean ok = playersService.authenticate(email);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Authentication failed");
        }
        return ResponseEntity.ok("Authenticated successfully!");
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get player by ID",
            description = "Fetches a player by their ID",
            responses = {
                    @ApiResponse(
                            description = "The player",
                            content = @Content(schema = @Schema(implementation = PlayerDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Player not found"
                    )
            }
    )
    public ResponseEntity<?> getPlayer(
            @Parameter(
                    description = "ID of the player to fetch",
                    required = true
            )
            @PathVariable("id") Long id
    ) {
        PlayerDto player = playersService.findOne(id);
        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Player not found with id: " + id);
        }

        return ResponseEntity.ok(player);
    }

    @PostMapping
    @Operation(
            summary = "Add a new player",
            description = "Creates a new player in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Player created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid player data"
                    )
            }
    )
    public ResponseEntity<?> addPlayer(
            @Parameter(
                    description = "Player object to add",
                    required = true
            ) PlayerDto player
    ) {
        if (player == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid player data");
        }

        playersService.save(player);
        return ResponseEntity.ok("Player created: " + player.getId());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a player",
            description = "Updates an existing player's information",
            responses = {
                    @ApiResponse(
                            description = "Updated player",
                            content = @Content(schema = @Schema(implementation = PlayerDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Player not found"
                    )
            }
    )
    public ResponseEntity<?> updatePlayer(
            @Parameter(
                    description = "ID of the player to update",
                    required = true
            )
            @PathVariable("id") Long id,
            @Parameter(
                    description = "Updated player object",
                    required = true
            ) PlayerDto player
    ) {
        PlayerDto existing = playersService.findOne(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Player not found");
        }

        player.setId(id);
        PlayerDto updated = playersService.update(player);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a player",
            description = "Deletes a player by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Player deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Player not found"
                    )
            }
    )
    public ResponseEntity<?> deletePlayer(
            @Parameter(
                    description = "ID of the player to delete",
                    required = true
            )
            @PathVariable("id") Long id
    ) {
        PlayerDto existing = playersService.findOne(id);
        if (existing == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Player not found");
        }

        playersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/quizzes")
    @Operation(
            summary = "Get quizzes of a player",
            description = "Fetches all quizzes authored by a given player",
            responses = {
                    @ApiResponse(
                            description = "List of quizzes",
                            content = @Content(schema = @Schema(implementation = QuizDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No quizzes found for player"
                    )
            }
    )
    public ResponseEntity<?>  getPlayerQuizzes(
            @Parameter(
                    description = "ID of the player",
                    required = true
            )
            @PathVariable("id") Long id
    ) {
        List<QuizDto> quizzes = playersService.findQuizByPlayer(id);
        if (quizzes.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No quizzes found for player " + id);
        }

        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}/participations")
    @Operation(
            summary = "Get participations of a player",
            description = "Fetches all participations of a given player",
            responses = {
                    @ApiResponse(
                            description = "List of participations",
                            content = @Content(schema = @Schema(implementation = ParticipationDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No participations found for player"
                    )
            }
    )
    public ResponseEntity<?> getPlayerParticipations(
            @Parameter(
                    description = "ID of the player",
                    required = true
            )
            @PathVariable("id") Long id
    ) {
        List<ParticipationDto> participations = playersService.findParticipationsByPlayer(id);
        if (participations.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No participations found for player " + id);
        }

        return ResponseEntity.ok(participations);
    }

}

