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

    public Question(
            String body,
            int timeLimit
    ) {
        this.body = body;
        this.timeLimit=  timeLimit;
    }

}

