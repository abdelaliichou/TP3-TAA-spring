package com.example.springtp.domain;

public class SQLQueries {
    public static enum Roles {
        STUDENT, TEACHER
    }

    final public static String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    final public static String playerFindByID = "SELECT p FROM Player p WHERE p.email = :email";
    final public static String playerFindByEmail = "SELECT p FROM Player p WHERE p.email = :email";
    final public static String playerFindQuizByPlayer = "SELECT q FROM Quiz q WHERE q.author.id = :playerId";
    final public static String playerFindParticipationsByPlayer = "SELECT p FROM Participation p WHERE p.player.id = :playerId";
    final public static String playerAuthenticate = "SELECT COUNT(p) FROM Player p WHERE p.email = :email";

    // --- JPQL Queries for Quiz ---
    final public static String quizFindById = "SELECT q FROM Quiz q WHERE q.id = :id";

    final public static String quizFindAll = "SELECT q FROM Quiz q ORDER BY q.id DESC";

    final public static String quizFindByAuthor = "SELECT q FROM Quiz q WHERE q.author.id = :authorId";

    final public static String quizFindWithQuestions = "SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.id = :quizId";

    final public static String quizDeleteById = "DELETE FROM Quiz q WHERE q.id = :id";

    final public static String participationFindByQuiz = "SELECT p FROM Participation p WHERE p.quiz.id = :quizId";

    final public static String quizFindByAuthorAndTitle = "SELECT q FROM Quiz q WHERE q.author.id = :authorId AND q.titre = :titre";

    final public static String creatQuiz = "INSERT INTO quiz (author_id, titre, description) VALUES (:authorId, :titre, :description)";

    final public static String addQuestionToQuiz = "INSERT INTO question (quiz_id, content, other_fields...) VALUES (:quizId, :content, :otherValues)";

    final public static String removeQuestionFromQuiz = "DELETE FROM question WHERE id = :questionId AND quiz_id = :quizId";

    // --- JPQL Queries for Response ---
    final public static String responsesFindWithQuestions = "SELECT p FROM Response p WHERE p.question.id = :questionId";

    final public static String correctResponsesByQuestion = "SELECT p FROM response p WHERE p.question.id = :questionId AND r.isCorrect = true";

    // --- JPQL Queries for Question ---
    final public static String questionsFindByQuiz = "SELECT p FROM Question p WHERE p.quiz.id = :quizId";

    // --- JPQL Queries for Participation ---
    final public static String calculateScore = """
        SELECT COUNT(r.id)
        FROM participation_responses pr
        JOIN response r ON r.id = pr.response_id
        WHERE pr.participation_id = :participationId
        AND r.is_correct = true
    """;

    // TODO same jpql queries for -> Participation | Quiz | Question | Response

}