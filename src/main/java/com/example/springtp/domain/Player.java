package com.example.springtp.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public Player() {}

    public Player(
            String name,
            String role,
            String email
    ) {
        this.role = role;
        this.name =  name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }

}
