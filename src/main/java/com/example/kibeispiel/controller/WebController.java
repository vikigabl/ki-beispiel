package com.example.kibeispiel.controller;

import com.example.kibeispiel.entity.Order;
import com.example.kibeispiel.entity.User;
import com.example.kibeispiel.service.MenuService;
import com.example.kibeispiel.service.OrderService;
import com.example.kibeispiel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Web Controller for the GUI Frontend.
 */
@Controller
public class WebController {

    private final MenuService menuService;
    private final OrderService orderService;
    private final UserService userService;

    public WebController(MenuService menuService, OrderService orderService, UserService userService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        return "index";
    }

    @GetMapping("/menus")
    public String menus(Model model) {
        model.addAttribute("menus", menuService.findAll());
        return "menus";
    }

    @GetMapping("/order")
    public String orderForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("menus", menuService.findAll());
        model.addAttribute("user", loggedInUser);
        return "order";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam Long menuId,
                              @RequestParam String orderDate,
                              @RequestParam String location,
                              @RequestParam int quantity,
                              HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        
        menuService.findById(menuId).ifPresent(menu -> {
            Order order = new Order(menu, LocalDate.parse(orderDate), location, quantity, loggedInUser);
            orderService.save(order);
        });
        
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String loginForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        return userService.authenticate(username, password)
                .map(user -> {
                    session.setAttribute("user", user);
                    return "redirect:/dashboard";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Ungültiger Benutzername oder Passwort");
                    return "login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        
        List<Order> upcomingOrders = orderService.findUpcomingByUser(loggedInUser);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("orders", upcomingOrders);
        return "dashboard";
    }

    @PostMapping("/order/cancel")
    public String cancelOrder(@RequestParam Long orderId, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        
        orderService.findById(orderId).ifPresent(order -> {
            if (Objects.equals(order.getUser().getId(), loggedInUser.getId())) {
                orderService.deleteById(orderId);
            }
        });
        
        return "redirect:/dashboard";
    }
}
