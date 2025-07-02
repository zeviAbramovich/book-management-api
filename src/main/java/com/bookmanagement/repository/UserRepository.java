package com.bookmanagement.repository;

import com.bookmanagement.dto.UserOrderStatsDTO;
import com.bookmanagement.model.Book;
import com.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM User u WHERE SIZE(u.orders) > :orderCount")
    List<User> findUsersWithMoreThanOrders(@Param("orderCount") int orderCount);

    @Query("SELECT new com.bookmanagement.dto.UserOrderStatsDTO(u.id, u.username, " +
            "COUNT(o), COALESCE(SUM(o.totalAmount), 0)) " +
            "FROM User u LEFT JOIN u.orders o " +
            "GROUP BY u.id, u.username")
    List<UserOrderStatsDTO> findUserOrderStatistics();

    @Query("SELECT u FROM User u WHERE u.orders IS EMPTY")
    List<User> findUsersWithoutOrders();
}
