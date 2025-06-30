package com.bookmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author must not be blank")
    @Column(nullable = false)
    private String author;

    @NotNull(message = "Published year is required")
    @Min(value = 1500, message = "Published year must be no earlier than 1500")
    // Current year validation is handled in the service layer
    private Integer publishedYear;
} 