package com.bookmanagement.service;

import com.bookmanagement.dto.OrderCreateDTO;
import com.bookmanagement.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    // Basic CRUD
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Long id);
    OrderResponseDTO createOrder(OrderCreateDTO orderDto);
    OrderResponseDTO updateOrder(Long id, OrderCreateDTO orderDto);
    void deleteOrder(Long id);

    // Business Logic
    OrderResponseDTO updateOrderStatus(Long id, String status);
    boolean isOrderOwner(Long orderId, String username);
}
