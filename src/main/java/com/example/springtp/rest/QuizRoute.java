package com.example.springtp.rest;

import com.example.springtp.dto.ParticipationDto;
import com.example.springtp.dto.QuestionDto;
import com.example.springtp.dto.QuizDto;
import com.example.springtp.service.QuizService;
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
@RequestMapping("/quizzes")
@Tag(name = "Quizzes", description = "Endpoints for managing quizzes")
public class QuizRoute {

    QuizService quizService;

    public QuizRoute(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    @Operation(
            summary = "Get all quizzes",
            description = "Returns a list of all quizzes",
            responses = {
                    @ApiResponse(
                            description = "List of quizzes",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = QuizDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get quiz by ID",
            description = "Fetches a quiz by its ID",
            responses = {
                    @ApiResponse(
                            description = "The quiz",
                            content = @Content(schema = @Schema(implementation = QuizDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quiz not found"
                    )
            }
    )
    public ResponseEntity<?> getQuiz(
            @Parameter(
                    description = "ID of the quiz to fetch",
                    required = true
            )
            @PathVariable("id") Long id
    ) {
        QuizDto quiz = quizService.findOne(id);
        if (quiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Quiz not found with id: " + id);
        }

        return ResponseEntity.ok(quiz);
    }

    @PostMapping
    @Operation(
            summary = "Create a new quiz",
            description = "Creates a new quiz in the system",
            responses = {
                    @ApiResponse(description = "Quiz created successfully"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid quiz data"
                    )
            }
    )
    public ResponseEntity<?> createQuiz(
            @Parameter(
                    description = "Quiz object to create",
                    required = true
            ) QuizDto quiz
    ) {
        if (quiz == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid quiz data");
        }

        quizService.save(quiz);
        return ResponseEntity.ok("Quizz created:" + quiz.getId());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a quiz",
            description = "Updates an existing quiz by ID",
            responses = {
                    @ApiResponse(
                            description = "Updated quiz",
                            content = @Content(schema = @Schema(implementation = QuizDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quiz not found"
                    )
            }
    )
    public ResponseEntity<?> updateQuiz(
            @Parameter(
                    description = "ID of the quiz to update",
                    required = true
            )
            @PathVariable("id") Long id,
            @Parameter(
                    description = "Updated quiz object",
                    required = true
            ) QuizDto quiz
    ) {
        QuizDto existing = quizService.findOne(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Quiz not found with id: " + id);
        }

        quiz.setId(id);
        QuizDto updated = quizService.update(quiz);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a quiz",
            description = "Deletes a quiz by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Quiz deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quiz not found"
                    )
            }
    )
    public ResponseEntity<?> deleteQuiz(
            @Parameter(
                    description = "ID of the quiz to delete",
                    required = true
            )
            @PathVariable("id") Long id
    ) {
        QuizDto existing = quizService.findOne(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Quiz not found with id: " + id);
        }

        quizService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    @Operation(
            summary = "Get quizzes by author",
            description = "Fetches all quizzes created by a specific author",
            responses = {
                    @ApiResponse(
                            description = "List of quizzes",
                            content = @Content(schema = @Schema(implementation = QuizDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No quizzes found for this author"
                    )
            }
    )
    public ResponseEntity<?> getQuizzesByAuthor(
            @Parameter(
                    description = "ID of the author",
                    required = true
            )
            @PathVariable("authorId") Long authorId
    ) {
        List<QuizDto> quizzes = quizService.findByAuteur(authorId);
        if (quizzes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No quizzes found for author " + authorId);
        }

        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}/questions")
    @Operation(
            summary = "Get questions of a quiz",
            description = "Fetches all questions belonging to a quiz",
            responses = {
                    @ApiResponse(
                            description = "List of questions",
                            content = @Content(schema = @Schema(implementation = QuestionDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No questions found for this quiz"
                    )
            }
    )
    public ResponseEntity<?> getQuestionsByQuiz(
            @Parameter(
                    description = "ID of the quiz",
                    required = true
            )
            @PathVariable("id") Long quizId
    ) {
        List<QuestionDto> questions = quizService.findQuestionsByQuiz(quizId);
        if (questions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No questions found for quiz " + quizId);
        }

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}/participations")
    @Operation(
            summary = "Get participations of a quiz",
            description = "Fetches all participations of a quiz",
            responses = {
                    @ApiResponse(
                            description = "List of participations",
                            content = @Content(schema = @Schema(implementation = ParticipationDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No participations found for this quiz"
                    )
            }
    )
    public ResponseEntity<?> getParticipationsByQuiz(
            @Parameter(
                    description = "ID of the quiz",
                    required = true
            )
            @PathVariable("id") Long quizId
    ) {
        List<ParticipationDto> participations = quizService.findParticipationsByQuiz(quizId);
        if (participations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No participations found for quiz " + quizId);
        }

        return ResponseEntity.ok(participations);
    }

    @PostMapping("/author/{authorId}")
    @Operation(
            summary = "Create a quiz with a specific author",
            description = "Creates a quiz with title and description associated with an author",
            responses = {
                    @ApiResponse(description = "Quiz created successfully"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Failed to create quiz"
                    )
            }
    )
    public ResponseEntity<?> createQuizWithAuthor(
            @Parameter(
                    description = "ID of the author",
                    required = true
            )
            @PathVariable("authorId") Long authorId,
            @Parameter(
                    description = "Title of the quiz",
                    required = true
            )
            @PathVariable("title") String title,
            @Parameter(
                    description = "Description of the quiz",
                    required = true
            )
            @PathVariable("description") String description
    ) {
        QuizDto quiz = quizService.createQuiz(authorId, title, description);
        if (quiz == null) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create quiz");
        }

        return ResponseEntity.ok("User with id:" + authorId + " created the quiz with id:" + quiz.getId());
    }

    @PostMapping("/{id}/questions")
    @Operation(
            summary = "Add question to a quiz",
            description = "Adds a new question to an existing quiz",
            responses = {
                    @ApiResponse(description = "Question added successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quiz not found"
                    )
            }
    )
    public ResponseEntity<?> addQuestionToQuiz(
            @Parameter(
                    description = "ID of the quiz",
                    required = true
            )
            @PathVariable("id") Long quizId,
            @Parameter(
                    description = "Question object to add",
                    required = true
            ) QuestionDto question
    ) {
        quizService.addQuestionToQuiz(quizId, question);
        return ResponseEntity.ok("Question added to quiz " + quizId);
    }

    @DeleteMapping("/{quizId}/questions/{questionId}")
    @Operation(
            summary = "Remove a question from a quiz",
            description = "Removes a question from a quiz by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Question removed successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Quiz or question not found"
                    )
            }
    )
    public ResponseEntity<?> removeQuestionFromQuiz(
            @Parameter(
                    description = "ID of the quiz",
                    required = true
            )
            @PathVariable("quizId") Long quizId,
            @Parameter(
                    description = "ID of the question to remove",
                    required = true
            )
            @PathVariable("questionId") Long questionId
    ) {
        quizService.removeQuestionFromQuiz(quizId, questionId);
        return ResponseEntity.noContent().build();
    }

}
