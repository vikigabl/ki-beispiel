package com.example.kibeispiel.controller;

import com.example.kibeispiel.entity.Menu;
import com.example.kibeispiel.entity.Order;
import com.example.kibeispiel.entity.User;
import com.example.kibeispiel.repository.MenuRepository;
import com.example.kibeispiel.repository.OrderRepository;
import com.example.kibeispiel.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Menu testMenu;
    private User testUser;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        menuRepository.deleteAll();
        userRepository.deleteAll();
        
        testMenu = menuRepository.save(new Menu("Test Menu", "Test Description"));
        testUser = userRepository.save(new User("testuser", "testpass"));
    }

    @Test
    void shouldGetAllOrders() throws Exception {
        Order order = new Order(testMenu, LocalDate.now().plusDays(1), "Test Location", 2, testUser);
        orderRepository.save(order);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldGetOrderById() throws Exception {
        Order order = new Order(testMenu, LocalDate.now().plusDays(1), "Test Location", 2, testUser);
        Order savedOrder = orderRepository.save(order);

        mockMvc.perform(get("/api/orders/" + savedOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location", is("Test Location")))
                .andExpect(jsonPath("$.quantity", is(2)));
    }

    @Test
    void shouldReturn404WhenOrderNotFound() throws Exception {
        mockMvc.perform(get("/api/orders/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        Order order = new Order(testMenu, LocalDate.now().plusDays(1), "Test Location", 2, testUser);
        Order savedOrder = orderRepository.save(order);

        mockMvc.perform(delete("/api/orders/" + savedOrder.getId()))
                .andExpect(status().isNoContent());
    }
}
