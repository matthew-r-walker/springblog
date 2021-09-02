package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.Verify;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result){
        String hash = passwordEncoder.encode(user.getPassword());

//      working on validation
        if (users.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "user.email", "This email already exists");
        }
        if (users.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "user.username", "This username already exists");
        }

        if (result.hasErrors()) {
            return "users/sign-up";
        }
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }
}