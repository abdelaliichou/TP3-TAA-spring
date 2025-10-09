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
public class QuizDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private PlayerDto author;
    private List<QuestionDto> questions = new ArrayList<>();
    private List<ParticipationDto> participations = new ArrayList<>();

    public QuizDto(
            String title,
            String description
    ) {
        this.title = title;
        this.description=  description;
    }
}
