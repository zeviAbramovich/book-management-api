package com.bookmanagement.service;

import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book createBook(Book book) {
        validatePublishedYear(book.getPublishedYear());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existing = getBookById(id);
        validatePublishedYear(book.getPublishedYear());
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublishedYear(book.getPublishedYear());
        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        Book existing = getBookById(id);
        bookRepository.delete(existing);
    }

    private void validatePublishedYear(Integer year) {
        int currentYear = Year.now().getValue();
        if (year == null || year < 1500 || year > currentYear) {
            throw new IllegalArgumentException("Published year must be between 1500 and " + currentYear);
        }
    }
} 