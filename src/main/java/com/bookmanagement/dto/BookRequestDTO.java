package com.bookmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    @Schema(description = "Title", example = "Clean Code")
    private String title;

    @Schema(description = "Author", example = "Robert C. Martin")
    private String author;

    @Schema(description = "Published year", example = "2008")
    private Integer publishedYear;
}
