package com.boostmytool.beststore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boostmytool.beststore.models.UserDto;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping({ "", "/" })
    public String showRegistrationPage() {
        return "users/index"; // Return the correct template path
    }

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "users/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "users/register";
        }
        userDetailsManager.createUser(
            User.withUsername(userDto.getUsername())
                .password("{noop}" + userDto.getPassword())
                .roles(userDto.getRole())
                .build()
        );
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "users/login"; 
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("userDto") UserDto userDto) {
        // Check if username or password is empty
        if (StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPassword())) {
            return "redirect:/users/login?error=empty";
        }

        UserDetails userDetails;
        try {
            // Load user details by username
            userDetails = userDetailsManager.loadUserByUsername(userDto.getUsername());
        } catch (UsernameNotFoundException e) {
            // User not found, redirect back to login page
            return "redirect:/users/login?error=invalid";
        }

        // Check if the password matches
        if (!userDetails.getPassword().equals(userDto.getPassword())) {
            // Incorrect password, redirect back to login page
            return "redirect:/users/login?error=invalid";
        }

        // Password matches, redirect to "/students"
        return "redirect:/students";
    }
}
