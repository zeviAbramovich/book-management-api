package com.bookmanagement.controller;

import com.bookmanagement.model.Book;
import com.bookmanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book Management", description = "Operations for managing books in the library")
@SecurityRequirement(name = "basicAuth")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(
            summary = "Get all books",
            description = "Retrieve a list of all books in the library"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of books",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(
            summary = "Get book by ID",
            description = "Retrieve a specific book by its unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully found the book",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    public Book getBookById(
            @Parameter(description = "ID of the book to retrieve", required = true)
            @PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(
            summary = "Create a new book",
            description = "Add a new book to the library collection"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Book successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    public ResponseEntity<Book> createBook(
            @Parameter(description = "Book object to be created", required = true)
            @Valid @RequestBody Book book) {
        Book created = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(
            summary = "Update an existing book",
            description = "Update book information by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    public Book updateBook(
            @Parameter(description = "ID of the book to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated book object", required = true)
            @Valid @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete a book",
            description = "Remove a book from the library collection"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Book successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete", required = true)
            @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
} 