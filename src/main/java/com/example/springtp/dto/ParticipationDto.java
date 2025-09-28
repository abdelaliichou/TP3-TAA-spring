package com.example.springtp.dto;

import java.io.Serializable;

public class ParticipationDto implements Serializable {
    private Long id;
    private String nickname;
    private int score;
    private PlayerDto player;
    private QuizDto quiz;


    public ParticipationDto() {}

    public ParticipationDto(
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

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    public QuizDto getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizDto quiz) {
        this.quiz = quiz;
    }
}
