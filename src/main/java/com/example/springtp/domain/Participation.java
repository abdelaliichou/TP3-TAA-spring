package com.example.springtp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

// class to link a player with a quiz that he played

@Entity
public class Participation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private int score;

    // one player can participate in multiple participation
    @ManyToOne
    private Player player;

    // multiple participation belongs to a quiz
    @ManyToOne
    private Quiz quiz;


    public Participation() {}

    public Participation(
            String nickname,
            int score
    ) {
        this.nickname = nickname;
        this.score=  score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickname;
    }
    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
