package com.example.springtp.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String body;
    private int timeLimit;

    @ManyToOne
    private Quiz quiz;

    // a question has multiple responses
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Response> reponses = new ArrayList<>();


    public Question() {}

    public Question(
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Response> getReponses() {
        return reponses;
    }

    public void setReponses(List<Response> reponses) {
        this.reponses = reponses;
    }

}

