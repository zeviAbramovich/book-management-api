package com.bookmanagement.service;

import com.bookmanagement.dto.BookRequestDTO;
import com.bookmanagement.dto.BookSummaryDTO;
import com.bookmanagement.model.Book;
import java.util.List;

public interface BookService {
    List<BookSummaryDTO> getAllBooks();
    BookSummaryDTO getBookById(Long id);
    BookSummaryDTO createBook(BookRequestDTO bookDto);
    BookSummaryDTO updateBook(Long id, BookRequestDTO book);
    void deleteBook(Long id);
} 