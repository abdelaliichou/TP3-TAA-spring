package com.example.springtp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerDto implements Serializable {

    private Long id;
    private String name;
    private String role;
    private String email;
    private List<QuizDto> quizzes = new ArrayList<>();
    private List<ParticipationDto> participations = new ArrayList<>();


    public PlayerDto() {}

    public PlayerDto(
            String name,
            String role,
            String email
    ) {
        this.role = role;
        this.name =  name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<QuizDto> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<QuizDto> quizzes) {
        this.quizzes = quizzes;
    }

    public List<ParticipationDto> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationDto> participations) {
        this.participations = participations;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }

}
