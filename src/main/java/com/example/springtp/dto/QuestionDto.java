package com.example.springtp.dto;

import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto implements Serializable {

    private Long id;
    private String body;
    private int timeLimit;
    private QuizDto quiz;
    private List<ResponseDto> reponses = new ArrayList<>();

    public QuestionDto(
            String body,
            int timeLimit
    ) {
        this.body = body;
        this.timeLimit=  timeLimit;
    }

}

