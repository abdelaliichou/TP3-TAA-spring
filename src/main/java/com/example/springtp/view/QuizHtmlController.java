package com.example.springtp.view;

import com.example.springtp.dto.PlayerDto;
import com.example.springtp.dto.QuizDto;
import com.example.springtp.service.PlayersService;
import com.example.springtp.service.QuizService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/quiz")
@PreAuthorize("hasRole('TEACHER')")
public class QuizHtmlController {

    private final PlayersService playersService;

    private final QuizService quizService;

    public QuizHtmlController(PlayersService playersService, QuizService quizService) {
        this.playersService = playersService;
        this.quizService = quizService;
    }

    @GetMapping
    public String listQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "allQuizzes";
    }

    @GetMapping("/form")
    public String showQuizForm(Model model) {
        model.addAttribute("players", playersService.findAll());
        return "quizForm";
    }

    @PostMapping("/save")
    public String saveQuiz(
           @RequestParam String title,
           @RequestParam String description,
           @RequestParam Long authorId
    ) {
        PlayerDto author = playersService.findOne(authorId);
        QuizDto quiz = new QuizDto();
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setAuthor(author);
        quizService.save(quiz);
        return "redirect:/view/quiz";
    }

    @PostMapping("/delete")
    public String deleteQuiz(@RequestParam Long id) {
        quizService.deleteById(id);
        return "redirect:/view/quiz";
    }
}

