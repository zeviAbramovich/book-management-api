package com.bookmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDTO {
    @NotNull(message = "User ID is required")
    @Schema(description = "ID of the user placing the order", example = "1")
    private Long userId;

    @NotEmpty(message = "At least one book is required")
    @Schema(description = "List of book IDs to order")
    private List<Long> bookIds;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", message = "Total amount must be greater than 0")
    @Schema(description = "Total amount", example = "99.99")
    private BigDecimal totalAmount;
}
