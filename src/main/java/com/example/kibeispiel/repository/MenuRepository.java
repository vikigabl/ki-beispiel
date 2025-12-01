package com.example.kibeispiel.repository;

import com.example.kibeispiel.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Menu entity.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
