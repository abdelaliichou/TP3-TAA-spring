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
public class PlayerDto implements Serializable {

    private Long id;
    private String name;
    private String role;
    private String email;
    private List<QuizDto> quizzes = new ArrayList<>();
    private List<ParticipationDto> participations = new ArrayList<>();

    public PlayerDto(
            String name,
            String role,
            String email
    ) {
        this.role = role;
        this.name =  name;
        this.email = email;
    }

}
