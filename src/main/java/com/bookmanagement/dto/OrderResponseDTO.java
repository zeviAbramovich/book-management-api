package com.bookmanagement.dto;

import com.bookmanagement.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for order response")
public class OrderResponseDTO {
    @Schema(description = "Order ID", example = "1")
    private Long id;

    @Schema(description = "Total amount", example = "99.99")
    private BigDecimal totalAmount;

    @Schema(description = "Order date")
    private LocalDateTime orderDate;

    @Schema(description = "Order status", example = "PENDING")
    private OrderStatus status;

    @Schema(description = "User information")
    private UserSummaryDTO user;

    @Schema(description = "Books in this order")
    private List<BookSummaryDTO> books;
}
