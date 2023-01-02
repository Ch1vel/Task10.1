package com.example.Task10.controlles;

import com.example.Task10.models.User;
import com.example.Task10.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String user(Authentication authentication, Model model) {
        model.addAttribute("user",(User) authentication.getPrincipal());
        return "user";
    }

}
