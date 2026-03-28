package com.AndreGGomes.libraryWithAuth.repository;

import com.AndreGGomes.libraryWithAuth.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void save_shouldGenerateIdAutomatically() {
        Book book = new Book(null, "TestBook", "11111111111", "TestAuthor");
        Book savedBook = bookRepository.save(book);

        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void findAll_shouldReturnAllBooks(){
        bookRepository.save(new Book(null, "TestBook1", "111", "Author1"));
        bookRepository.save(new Book(null, "TestBook2", "222", "Author2"));

        assertThat(bookRepository.findAll()).hasSize(2);
    }

    @Test
    void deleteById_shouldDeleteBook(){
        Book savedBook = bookRepository.save(new Book(null, "TestBook", "111", "Auhtor1"));
        bookRepository.deleteById(savedBook.getId());

        assertThat(bookRepository.findById(savedBook.getId())).isEmpty();
    }
}
