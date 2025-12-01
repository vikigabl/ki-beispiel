package com.example.kibeispiel.repository;

import com.example.kibeispiel.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Item entity.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
