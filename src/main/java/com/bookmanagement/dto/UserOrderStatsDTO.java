package com.bookmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for user order statistics")
public class UserOrderStatsDTO {
    @Schema(description = "User ID", example = "1")
    private Long userId;

    @Schema(description = "Username", example = "john_doe")
    private String username;

    @Schema(description = "Total number of orders", example = "5")
    private Long orderCount;

    @Schema(description = "Total amount of all orders", example = "499.99")
    private BigDecimal totalAmount;
}
