package com.example.Task10.controlles;

import com.example.Task10.models.User;
import com.example.Task10.services.RoleService;
import com.example.Task10.services.UserService;
import com.example.Task10.util.UserValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

        private final UserService userService;
        private final RoleService roleService;

        private final UserValidation userValidation;

    public AdminController(UserService userService, RoleService roleService, UserValidation userValidation) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidation = userValidation;
    }

    @GetMapping("")
    public String userList(Model model) {
        model.addAttribute("users",userService.show());
        return "admin";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user,Model model) {
        model.addAttribute("roles",roleService.showRoles());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidation.validate(user,bindingResult);
        if(bindingResult.hasErrors()) return "add";
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("roles",roleService.showRoles());
        model.addAttribute("user",userService.show(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidation.validate(user,bindingResult);
        if(bindingResult.hasErrors()) return "edit";
        userService.update(user);
        return "redirect:/admin";
    }
}
