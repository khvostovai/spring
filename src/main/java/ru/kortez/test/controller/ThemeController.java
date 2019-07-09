package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kortez.test.domain.Theme;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.MessageRepository;
import ru.kortez.test.repos.ThemeRepository;

@Controller
public class ThemeController {

    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private MessageRepository messageRepository;

    //show list of themes
    @GetMapping("/themes")
    String themesList(Model model){
        model.addAttribute("themes", themeRepository.findAll());
        return "themes";
    }

    //add new theme
    @PostMapping("/themes")
    String addTheme(@AuthenticationPrincipal User user, Theme theme, Model model){
        if(theme != null){
            theme.setAuthor(user);
            themeRepository.save(theme);
        }
        return "redirect:/themes";
    }

    //show messages which belong theme
    @GetMapping("/theme/{theme}")
    String showTheme(@PathVariable Theme theme, Model model){
        if(theme != null){
            model.addAttribute("messages", messageRepository.findAllByTheme(theme));
            model.addAttribute("theme_id", theme.getId());
            return "messages";
        }
        return "redirect:/themes";
    }
}
