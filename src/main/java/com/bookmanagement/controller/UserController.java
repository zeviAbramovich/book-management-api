package com.bookmanagement.controller;

import com.bookmanagement.dto.OrderResponseDTO;
import com.bookmanagement.dto.UserCreateDTO;
import com.bookmanagement.dto.UserOrderStatsDTO;
import com.bookmanagement.dto.UserResponseDTO;
import com.bookmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Operations for managing users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", description = "Retrieve all users (Admin only)")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #id == authentication.principal.id)")
    @Operation(summary = "Get user by ID", description = "Retrieve user by ID (Admin or own user)")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Create new user", description = "Register a new user")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userDto) {
        UserResponseDTO created = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #id == authentication.principal.id)")
    @Operation(summary = "Get user orders", description = "Get all orders for a specific user")
    public List<OrderResponseDTO> getUserOrders(@PathVariable Long id) {
        return userService.getUserOrders(id);
    }

    // Custom Queries endpoints:
    @GetMapping("/with-orders-above/{count}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Users with many orders", description = "Get users with more than specified number of orders")
    public List<UserResponseDTO> getUsersWithMoreThanOrders(@PathVariable int count) {
        return userService.getUsersWithMoreThanOrders(count);
    }

    @GetMapping("/order-statistics")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "User order statistics", description = "Get order statistics for all users")
    public List<UserOrderStatsDTO> getUserOrderStatistics() {
        return userService.getUserOrderStatistics();
    }

    @GetMapping("/without-orders")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Users without orders", description = "Get users who haven't placed any orders")
    public List<UserResponseDTO> getUsersWithoutOrders() {
        return userService.getUsersWithoutOrders();
    }
}
