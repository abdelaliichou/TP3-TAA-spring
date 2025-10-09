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
public class Response implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String body;
    private Boolean isCorrect;

    // many responses belong to a question
    @ManyToOne
    private Question question;

    public Response(
            String body,
            Boolean isCorrect
    ) {
        this.body = body;
        this.isCorrect=  isCorrect;
    }

}

