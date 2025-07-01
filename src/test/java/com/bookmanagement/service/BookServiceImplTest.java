package com.bookmanagement.service;

import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    /*@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = Book.builder()
                .id(1L)
                .title("Test Title")
                .author("Test Author")
                .publishedYear(2000)
                .build();
    }

    @Test
    void getAllBooks_ReturnsList() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_Found() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book found = bookService.getBookById(1L);
        assertEquals(book.getTitle(), found.getTitle());
    }

    @Test
    void getBookById_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.getBookById(2L));
    }

    @Test
    void createBook_Valid() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book created = bookService.createBook(book);
        assertEquals(book.getTitle(), created.getTitle());
    }

    @Test
    void createBook_InvalidYear() {
        book.setPublishedYear(1400);
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(book));
    }

    @Test
    void updateBook_Valid() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book updated = bookService.updateBook(1L, book);
        assertEquals(book.getTitle(), updated.getTitle());
    }

    @Test
    void updateBook_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.updateBook(2L, book));
    }

    @Test
    void deleteBook_Valid() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);
        assertDoesNotThrow(() -> bookService.deleteBook(1L));
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void deleteBook_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.deleteBook(2L));
    }*/
} 