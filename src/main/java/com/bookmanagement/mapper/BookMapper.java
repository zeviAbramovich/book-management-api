package com.bookmanagement.mapper;

import com.bookmanagement.dto.BookSummaryDTO;
import com.bookmanagement.model.Book;
import com.bookmanagement.dto.BookRequestDTO;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookRequestDTO dto);

    BookSummaryDTO toSummaryDto(Book book);

    List<BookSummaryDTO> toSummaryDtoList(List<Book> books);

    void updateBookFromDto(BookRequestDTO book, @MappingTarget Book existing);
}
