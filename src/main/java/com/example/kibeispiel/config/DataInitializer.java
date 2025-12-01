package com.example.kibeispiel.config;

import com.example.kibeispiel.entity.Menu;
import com.example.kibeispiel.entity.User;
import com.example.kibeispiel.repository.MenuRepository;
import com.example.kibeispiel.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer to populate the database with sample menus and test user on application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public DataInitializer(MenuRepository menuRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (menuRepository.count() == 0) {
            menuRepository.save(new Menu("Menü 1: Wienerschnitzel", "Klassisches Wienerschnitzel mit Kartoffelsalat"));
            menuRepository.save(new Menu("Menü 2: Fisch", "Frischer Fisch mit Gemüse und Reis"));
        }
        
        if (userRepository.count() == 0) {
            userRepository.save(new User("user", "user"));
        }
    }
}
