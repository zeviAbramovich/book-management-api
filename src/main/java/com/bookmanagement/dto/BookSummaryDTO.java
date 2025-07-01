package com.bookmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Summary DTO for book information")
public class BookSummaryDTO {
    @Schema(description = "Book ID", example = "1")
    private Long id;

    @Schema(description = "Title", example = "Clean Code")
    private String title;

    @Schema(description = "Author", example = "Robert C. Martin")
    private String author;
}
