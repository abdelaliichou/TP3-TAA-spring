package com.example.springtp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private PlayerDto author;
    private List<QuestionDto> questions = new ArrayList<>();
    private List<ParticipationDto> participations = new ArrayList<>();


    public QuizDto() {}

    public QuizDto(
            String title,
            String description
    ) {
        this.title = title;
        this.description=  description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public PlayerDto getAuthor() {
        return author;
    }

    public void setAuthor(PlayerDto author) {
        this.author = author;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public List<ParticipationDto> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationDto> participations) {
        this.participations = participations;
    }
}
