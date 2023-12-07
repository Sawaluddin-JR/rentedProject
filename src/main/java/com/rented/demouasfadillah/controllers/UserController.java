package com.rented.demouasfadillah.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rented.demouasfadillah.models.User;
import com.rented.demouasfadillah.repositories.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String getRegPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        userRepository.save(user);
        model.addAttribute("message", "Submitted Successfully");
        return "register";
    }

    @GetMapping("/users")
    public String userPage(Model model) {
        List<User> listOfUsers = userRepository.findAll();
        model.addAttribute("user", listOfUsers);
        return "show-user";
    }
}
