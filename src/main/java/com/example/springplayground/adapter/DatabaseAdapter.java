package com.example.springplayground.adapter;

import com.example.springplayground.model.Book;
import com.example.springplayground.model.Genre;
import com.example.springplayground.port.incoming.BooksPort;
import com.example.springplayground.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import static com.example.springplayground.repository.specification.BooksSpecification.findByAuthor;
import static com.example.springplayground.repository.specification.BooksSpecification.findByGenre;

public class DatabaseAdapter implements BooksPort {

    private final BookRepository bookRepository;

    public DatabaseAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book get(String id) {
        return this.bookRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return this.bookRepository.findAll(findByGenre(genre));
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return this.bookRepository.findAll(findByAuthor(author));
    }

    @Override
    public List<Book> getBy(Genre genre, String author) {
        return this.bookRepository.findAll(findByAuthor(author).and(findByGenre(genre)));
    }
}
