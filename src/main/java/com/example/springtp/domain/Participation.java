package com.example.springtp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    public Participation(
            String nickname,
            int score
    ) {
        this.nickname = nickname;
        this.score=  score;
    }

}
