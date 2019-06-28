package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kortez.test.domain.Role;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null){
            model.addAttribute("tip", "user with this username is exist");
            return "/registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
