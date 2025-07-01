package com.bookmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "User entity representing a customer in the system")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Username must not be blank")
    @Column(name = "user_name", nullable = false, unique = true)
    @Schema(description = "Username for the user", example = "john_doe", required = true)
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Column(nullable = false, unique = true)
    @Schema(description = "Email address of the user", example = "john.doe@example.com", required = true)
    private String email;

    @NotBlank(message = "Full name must not be blank")
    @Column(name = "full_name", nullable = false)
    @Schema(description = "Full name of the user", example = "John Doe", required = true)
    private String fullName;

    @Column(name = "created_at")
    @Schema(description = "When the user was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    @Schema(description = "List of orders placed by this user", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Helper methods for managing the relationship
    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }
}
