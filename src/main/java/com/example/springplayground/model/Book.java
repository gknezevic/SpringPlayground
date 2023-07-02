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

    @Column
    private String author;

    private Book(String title, Genre genre, String author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public static Book create(String title, Genre genre, String author) {
        return new Book(title, genre, author);
    }
}
