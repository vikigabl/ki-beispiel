package com.example.kibeispiel.controller;

import com.example.kibeispiel.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web Controller for the GUI Frontend.
 */
@Controller
public class WebController {

    private final ItemService itemService;

    public WebController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "index";
    }
}
