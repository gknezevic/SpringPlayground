package com.example.springplayground.repository.specification;

import com.example.springplayground.model.Book;
import com.example.springplayground.model.Genre;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooksSpecification {

    public static Specification<Book> findByGenre(Genre genre) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), genre));
    }

    public static Specification<Book> findByAuthor(String author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), author);
    }
}
