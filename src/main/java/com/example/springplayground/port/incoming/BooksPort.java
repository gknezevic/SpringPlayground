package com.example.springplayground.port.incoming;

import com.example.springplayground.model.Book;
import com.example.springplayground.model.Genre;

import java.util.List;

public interface BooksPort {

    Book save(Book book);
    Book get(String id);
    List<Book> getByGenre(Genre genre);
}
