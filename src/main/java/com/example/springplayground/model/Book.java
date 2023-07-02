package com.example.springplayground.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Book(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }

    public static Book create(String title, Genre genre) {
        return new Book(title, genre);
    }
}
