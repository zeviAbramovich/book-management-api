package com.bookmanagement.controller;

import com.bookmanagement.dto.BookRequestDTO;
import com.bookmanagement.dto.BookSummaryDTO;
import com.bookmanagement.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@DisplayName("BookController Integration Tests")
class BookControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should get all books")
    @WithMockUser(roles = "USER")
    void getAllBooks_ShouldReturnBooks() throws Exception {
        BookSummaryDTO book = BookSummaryDTO.builder()
                .id(1L).title("Clean Code").author("Martin").publishedYear(2008).build();

        when(bookService.getAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Clean Code")));
    }

    @Test
    @DisplayName("Should create book")
    @WithMockUser(roles = "USER")
    void createBook_ShouldReturnCreated() throws Exception {
        BookRequestDTO request = BookRequestDTO.builder()
                .title("New Book").author("Author").publishedYear(2023).build();
        BookSummaryDTO response = BookSummaryDTO.builder()
                .id(1L).title("New Book").author("Author").publishedYear(2023).build();

        when(bookService.createBook(any(BookRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/books")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Book\",\"author\":\"Author\",\"publishedYear\":2023}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("New Book")));
    }

    @Test
    @DisplayName("Should delete book with admin role")
    @WithMockUser(roles = "ADMIN")
    void deleteBook_WithAdminRole_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/books/1")
                .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should reject delete with user role")
    @WithMockUser(roles = "USER")
    void deleteBook_WithUserRole_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(delete("/api/v1/books/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Should reject unauthenticated requests")
    void getAllBooks_WithoutAuth_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isUnauthorized());
    }
} 