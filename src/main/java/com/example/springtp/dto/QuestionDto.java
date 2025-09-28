package com.example.springtp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionDto implements Serializable {

    private Long id;
    private String body;
    private int timeLimit;
    private QuizDto quiz;
    private List<ResponseDto> reponses = new ArrayList<>();


    public QuestionDto() {}

    public QuestionDto(
            String body,
            int timeLimit
    ) {
        this.body = body;
        this.timeLimit=  timeLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public int getTimeLimit() {
        return this.timeLimit;
    }
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public QuizDto getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizDto quiz) {
        this.quiz = quiz;
    }

    public List<ResponseDto> getReponses() {
        return reponses;
    }

    public void setReponses(List<ResponseDto> reponses) {
        this.reponses = reponses;
    }

}

