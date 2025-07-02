package com.bookmanagement.service;

import com.bookmanagement.dto.OrderResponseDTO;
import com.bookmanagement.dto.UserCreateDTO;
import com.bookmanagement.dto.UserOrderStatsDTO;
import com.bookmanagement.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserCreateDTO userDto);

    // Relationships
    List<OrderResponseDTO> getUserOrders(Long userId);

    // Custom Queries (הדרישות המקוריות)
    List<UserResponseDTO> getUsersWithMoreThanOrders(int orderCount);
    List<UserOrderStatsDTO> getUserOrderStatistics();
    List<UserResponseDTO> getUsersWithoutOrders();
}
