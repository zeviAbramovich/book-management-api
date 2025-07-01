package com.bookmanagement.service;

import com.bookmanagement.dto.BookRequestDTO;
import com.bookmanagement.dto.BookSummaryDTO;
import com.bookmanagement.mapper.BookMapper;
import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookSummaryDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toSummaryDtoList(books);
    }

    @Override
    public BookSummaryDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found"));
        return bookMapper.toSummaryDto(book);
    }

    @Override
    public BookSummaryDTO createBook(BookRequestDTO bookDto) {
        validatePublishedYear(bookDto.getPublishedYear());
        Book book = bookMapper.toEntity(bookDto);
        Book saved = bookRepository.save(book);
        return bookMapper.toSummaryDto(saved);
    }

    @Override
    public BookSummaryDTO updateBook(Long id, BookRequestDTO book) {
        validatePublishedYear(book.getPublishedYear());
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found"));
        bookMapper.updateBookFromDto(book, existing);
        Book updated = bookRepository.save(existing);
        return bookMapper.toSummaryDto(updated);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    private void validatePublishedYear(Integer year) {
        int currentYear = Year.now().getValue();
        if (year == null || year < 1500 || year > currentYear) {
            throw new IllegalArgumentException("Published year must be between 1500 and " + currentYear);
        }
    }
} 