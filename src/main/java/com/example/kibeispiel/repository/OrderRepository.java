package com.example.kibeispiel.repository;

import com.example.kibeispiel.entity.Order;
import com.example.kibeispiel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * JPA Repository for Order entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByUserAndOrderDateGreaterThanEqual(User user, LocalDate date);
}
