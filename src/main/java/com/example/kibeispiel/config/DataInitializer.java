package com.example.kibeispiel.config;

import com.example.kibeispiel.entity.Menu;
import com.example.kibeispiel.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer to populate the database with sample menus on application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final MenuRepository menuRepository;

    public DataInitializer(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(String... args) {
        if (menuRepository.count() == 0) {
            menuRepository.save(new Menu("Menü 1: Wienerschnitzel", "Klassisches Wienerschnitzel mit Kartoffelsalat"));
            menuRepository.save(new Menu("Menü 2: Fisch", "Frischer Fisch mit Gemüse und Reis"));
        }
    }
}
