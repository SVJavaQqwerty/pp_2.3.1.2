package ru.svlid.pp_312.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svlid.pp_312.models.User;
import ru.svlid.pp_312.service.UserService;



@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user (@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/user/add")
    public String userGetAdd () {
        return "add";
    }
    @PostMapping("/user/add")
    public String addUser(@RequestParam String name, @RequestParam String lastName, @RequestParam String email, Model model) {
        User user = new User(name, lastName, email);
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/{id}")
    public String addId (@PathVariable(value = "id") Long id, Model model) {
        Iterable<User> user = userService.findAllById(id);
        model.addAttribute("users", user);
        return "details";
    }

    @GetMapping("user/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") Long id) {
        Iterable<User> user = userService.findAllById(id);
        model.addAttribute("users", user);
        return "edit";
    }

    @PutMapping("user/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }

    @DeleteMapping(value = "user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
