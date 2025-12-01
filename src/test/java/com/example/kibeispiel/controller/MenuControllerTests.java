package com.example.kibeispiel.controller;

import com.example.kibeispiel.entity.Menu;
import com.example.kibeispiel.repository.MenuRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        menuRepository.deleteAll();
    }

    @Test
    void shouldCreateMenu() throws Exception {
        Menu menu = new Menu("Menü 1: Wienerschnitzel", "Klassisches Wienerschnitzel");

        mockMvc.perform(post("/api/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menu)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Menü 1: Wienerschnitzel")))
                .andExpect(jsonPath("$.description", is("Klassisches Wienerschnitzel")));
    }

    @Test
    void shouldGetAllMenus() throws Exception {
        menuRepository.save(new Menu("Menü 1: Wienerschnitzel", "Klassisches Wienerschnitzel"));
        menuRepository.save(new Menu("Menü 2: Fisch", "Frischer Fisch"));

        mockMvc.perform(get("/api/menus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldGetMenuById() throws Exception {
        Menu savedMenu = menuRepository.save(new Menu("Menü 1: Wienerschnitzel", "Klassisches Wienerschnitzel"));

        mockMvc.perform(get("/api/menus/" + savedMenu.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Menü 1: Wienerschnitzel")));
    }

    @Test
    void shouldReturn404WhenMenuNotFound() throws Exception {
        mockMvc.perform(get("/api/menus/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateMenu() throws Exception {
        Menu savedMenu = menuRepository.save(new Menu("Original Name", "Original Description"));
        Menu updatedMenu = new Menu("Updated Name", "Updated Description");

        mockMvc.perform(put("/api/menus/" + savedMenu.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMenu)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Name")))
                .andExpect(jsonPath("$.description", is("Updated Description")));
    }

    @Test
    void shouldDeleteMenu() throws Exception {
        Menu savedMenu = menuRepository.save(new Menu("Menü 1: Wienerschnitzel", "Klassisches Wienerschnitzel"));

        mockMvc.perform(delete("/api/menus/" + savedMenu.getId()))
                .andExpect(status().isNoContent());
    }
}
