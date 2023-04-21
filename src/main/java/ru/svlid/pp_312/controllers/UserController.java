package ru.svlid.pp_312.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Transactional
    @GetMapping("/user")
    public String user (@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user";
    }
    @Transactional
    @GetMapping("/user/add")
    public String userGetAdd () {
        return "add";
    }
    @PostMapping("/user/add")
    public String addUser(@RequestParam String name, @RequestParam String lastName, @RequestParam String email, Model model) {
        User user = new User(name, lastName, email);

        userService.save(user);
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "redirect:/user";
    }
    @Transactional
    @GetMapping("/user/{id}")
    public String addId (@PathVariable(value = "id") Long id, Model model) {
        if (!userService.existsById(id)) {
            return "user";
        }
        Iterable<User> user = userService.findAllById(id);
/*        Optional<User> user = userRepo.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);*/
        model.addAttribute("users", user);
        return "details";
    }
    @Transactional
    @GetMapping("user/{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") Long id) {
        if (!userService.existsById(id)) {
            return "redirect:/user";
        }
        Iterable<User> user = userService.findAllById(id);
        model.addAttribute("users", user);
        return "edit";
    }
    @Transactional
    @PutMapping("user/{id}/edit")
    public String updateUser(@RequestParam String name, @RequestParam String lastName, @RequestParam String email,
                             @PathVariable Long id) {
        User user = userService.findById(id).orElseThrow();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.save(user);
        return "redirect:/user";
    }
    @Transactional
    @PostMapping("user/{id}/remove")
    public String removeUser(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow();
        userService.delete(user);
        return "redirect:/user";
    }
    @Transactional
    @DeleteMapping(value = "user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow();
        userService.delete(user);
        return "redirect:/user";
    }
}
