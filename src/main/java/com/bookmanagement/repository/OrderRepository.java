package com.bookmanagement.repository;

import com.bookmanagement.enums.OrderStatus;
import com.bookmanagement.model.Order;
import com.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUserUsername(String username);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByStatusIn(List<OrderStatus> statuses);
    List<Order> findByTotalAmountGreaterThan(BigDecimal amount);
    List<Order> findByTotalAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);
    List<Order> findByOrderDateAfter(LocalDateTime date);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
