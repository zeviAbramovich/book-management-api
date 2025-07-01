package com.bookmanagement.repository;

import com.bookmanagement.model.Order;
import com.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
