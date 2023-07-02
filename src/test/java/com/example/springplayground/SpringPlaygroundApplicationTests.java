package com.example.springplayground;

import com.example.springplayground.adapter.DatabaseAdapter;
import com.example.springplayground.model.Book;
import com.example.springplayground.model.Genre;
import com.example.springplayground.port.incoming.BooksPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringPlaygroundApplicationTests {

    private BooksPort booksPort;

    @BeforeEach
    public void beforeEach() {
        booksPort = new DatabaseAdapter();
    }

    @Test
    public void shouldReturnedStoredBookUsingIdTest() {
        Book book = Book.create("My Test book", Genre.COMEDY);
        Book savedBook = booksPort.save(book);
        Book fetchedBook = booksPort.get(savedBook.getId());
        assertNotNull(fetchedBook);
        assertEquals(savedBook.getId(), fetchedBook.getId());
    }

}
