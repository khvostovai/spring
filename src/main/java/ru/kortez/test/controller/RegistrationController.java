package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.UserRepository;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        System.out.println(user.getUsername());
        User findUseer = userRepo.findByUsername(user.getUsername());
        if (findUseer != null){
            model.addAttribute("tip", "user with this username is exist");
            return "/registration";
        }
        return "redirect:/login";
    }
}
