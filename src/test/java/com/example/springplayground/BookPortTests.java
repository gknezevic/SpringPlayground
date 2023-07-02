package com.example.springplayground;

import com.example.springplayground.adapter.DatabaseAdapter;
import com.example.springplayground.model.Book;
import com.example.springplayground.model.Genre;
import com.example.springplayground.port.incoming.BooksPort;
import com.example.springplayground.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.example.springplayground.model.Genre.HORROR;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookPortTests {

    private BooksPort booksPort;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void beforeEach() {
        booksPort = new DatabaseAdapter(bookRepository);
    }

    @Test
    void shouldReturnedStoredBookUsingIdTest() {
        Book book = Book.create("My Test book", Genre.COMEDY, "Unknown");
        Book savedBook = booksPort.save(book);
        Book fetchedBook = booksPort.get(savedBook.getId());
        assertNotNull(fetchedBook);
        assertEquals(savedBook.getId(), fetchedBook.getId());
    }

    @Test
    void shouldThrowExceptionIfBookIsNotFoundTest() {
        assertThrows(EntityNotFoundException.class, () -> booksPort.get("XYZ"));
    }

    @Test
    void shouldFetchAllHorrorBooksTest() {
        booksPort.save(Book.create("My Test book 1", HORROR, "Stephen King"));
        booksPort.save(Book.create("My Test book 2", Genre.COMEDY, "Unknown"));
        booksPort.save(Book.create("My Test book 3", HORROR, "Unknown"));

        List<Book> horrorBooks = booksPort.getByGenre(HORROR);
        assertEquals(2, horrorBooks.size());
    }

    @Test
    void shouldFetchAllBooksOfAuthorTest() {
        booksPort.save(Book.create("My Test book 1", HORROR, "Stephen King"));
        booksPort.save(Book.create("My Test book 2", Genre.COMEDY, "Stephen King"));
        booksPort.save(Book.create("My Test book 3", HORROR, "Bram Stoker"));
        booksPort.save(Book.create("My Test book 4", HORROR, "Stephen King"));

        List<Book> horrorBooks = booksPort.getByAuthor("Stephen King");
        assertEquals(3, horrorBooks.size());
    }

    @Test
    void shouldFetchAllBooksOfAuthorAndSpecificGenreTest() {
        booksPort.save(Book.create("My Test book 1", HORROR, "Stephen King"));
        booksPort.save(Book.create("My Test book 2", Genre.COMEDY, "Stephen King"));
        booksPort.save(Book.create("My Test book 3", HORROR, "Bram Stoker"));
        booksPort.save(Book.create("My Test book 4", HORROR, "Stephen King"));

        List<Book> horrorBooks = booksPort.getBy(HORROR, "Stephen King");
        assertEquals(2, horrorBooks.size());
    }

}
