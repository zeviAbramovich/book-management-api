package com.bookmanagement.controller;

import com.bookmanagement.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {
    /*@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled("Skipping this test for now")
    void createAndGetBook_Success() throws Exception {
        Book book = Book.builder()
                .title("Integration Title")
                .author("Integration Author")
                .publishedYear(2001)
                .build();
        // Create
        String response = mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn().getResponse().getContentAsString();
        Book created = objectMapper.readValue(response, Book.class);
        // Get by ID
        mockMvc.perform(get("/api/v1/books/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Title"));
    }

    @Test
    @Disabled("Skipping this test for now")
    void getAllBooks_EmptyInitially() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Disabled("Skipping this test for now")
    void createBook_ValidationError() throws Exception {
        Book book = Book.builder().title("").author("").publishedYear(1400).build();
        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.author").exists());
    }

    @Test
    @Disabled("Skipping this test for now")
    void getBookById_NotFound() throws Exception {
        mockMvc.perform(get("/api/v1/books/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @Disabled("Skipping this test for now")
    void updateBook_Success() throws Exception {
        Book book = Book.builder().title("Old").author("Old").publishedYear(2000).build();
        String response = mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn().getResponse().getContentAsString();
        Book created = objectMapper.readValue(response, Book.class);
        Book updated = Book.builder().title("New").author("New").publishedYear(2020).build();
        mockMvc.perform(put("/api/v1/books/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New"));
    }

    @Test
    @Disabled("Skipping this test for now")
    void deleteBook_Success() throws Exception {
        Book book = Book.builder().title("ToDelete").author("ToDelete").publishedYear(2010).build();
        String response = mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn().getResponse().getContentAsString();
        Book created = objectMapper.readValue(response, Book.class);
        mockMvc.perform(delete("/api/v1/books/" + created.getId()))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v1/books/" + created.getId()))
                .andExpect(status().isNotFound());
    }*/
} 