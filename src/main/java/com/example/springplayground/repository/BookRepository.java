package com.example.springplayground.repository;

import com.example.springplayground.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
