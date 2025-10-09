package com.example.springtp.dto;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto implements Serializable {
    private Long id;
    private String nickname;
    private int score;
    private PlayerDto player;
    private QuizDto quiz;

    public ParticipationDto(
            String nickname,
            int score
    ) {
        this.nickname = nickname;
        this.score=  score;
    }

}
