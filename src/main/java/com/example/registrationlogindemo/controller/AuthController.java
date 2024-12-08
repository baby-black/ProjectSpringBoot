package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
public class AuthController {

    private final UserService userService;
    private final ProductServiceImpl productServiceImpl;

    public AuthController(UserService userService, ProductServiceImpl productServiceImpl) {
        this.userService = userService;
        this.productServiceImpl = productServiceImpl;
    }

    @Operation(summary = "View the homepage", description = "This endpoint returns the homepage.")
    @GetMapping("index")
    public String home() {
        return "index";
    }


    @Operation(summary = "Show login form", description = "This endpoint returns the login form.")
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @Operation(summary = "Show registration form", description = "This endpoint returns the registration form.")
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @Operation(summary = "Register user", description = "This endpoint allows users to register.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @Operation(summary = "List registered users", description = "This endpoint returns a list of all registered users.")
    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users"; // This will return the users.html template
    }

}
