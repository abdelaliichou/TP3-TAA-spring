package com.example.springtp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    // many quizzes can be created by one player who is a teacher
    @ManyToOne
    private Player author;

    // a quiz contains multiple players
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    // a quiz contains multiple participations
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Participation> participations = new ArrayList<>();

    public Quiz(
            String title,
            String description
    ) {
        this.title = title;
        this.description=  description;
    }

}
