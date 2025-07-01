package com.bookmanagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Book entity representing a book in the library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the book", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Column(nullable = false)
    @Schema(description = "Title of the book", example = "Clean Code", required = true)
    private String title;

    @NotBlank(message = "Author must not be blank")
    @Column(nullable = false)
    @Schema(description = "Author of the book", example = "Robert C. Martin", required = true)
    private String author;

    @NotNull(message = "Published year is required")
    @Min(value = 1500, message = "Published year must be no earlier than 1500")
    @Schema(description = "Year the book was published", example = "2008", required = true, minimum = "1500")
    @Column(name = "published_year")
    private Integer publishedYear;
} 