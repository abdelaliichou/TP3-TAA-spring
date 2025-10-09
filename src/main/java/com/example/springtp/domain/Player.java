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
public class Player implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String name;
    private String role;
    private String email;

    // a player who is a teacher can create multiple quizzes
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    // a player can have many participations
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Participation> participations = new ArrayList<>();

    public Player(
            String name,
            String role,
            String email
    ) {
        this.role = role;
        this.name =  name;
        this.email = email;
    }

}
