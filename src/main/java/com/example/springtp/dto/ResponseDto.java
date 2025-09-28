package com.example.springtp.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {

    private Long id;
    private String body;
    private Boolean isCorrect;
    private QuestionDto question;


    public ResponseDto() {}

    public ResponseDto(
            String body,
            Boolean isCorrect
    ) {
        this.body = body;
        this.isCorrect=  isCorrect;
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

    public Boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }
}

