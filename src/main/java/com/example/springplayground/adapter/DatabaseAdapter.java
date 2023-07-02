package com.example.springplayground.adapter;

import com.example.springplayground.model.Book;
import com.example.springplayground.model.Genre;
import com.example.springplayground.port.incoming.BooksPort;

import java.util.List;

public class DatabaseAdapter implements BooksPort {

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Book get(String id) {
        return null;
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return null;
    }
}
