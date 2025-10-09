package com.example.springtp.dto;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto implements Serializable {

    private Long id;
    private String body;
    private Boolean isCorrect;
    private QuestionDto question;

    public ResponseDto(
            String body,
            Boolean isCorrect
    ) {
        this.body = body;
        this.isCorrect=  isCorrect;
    }
}

