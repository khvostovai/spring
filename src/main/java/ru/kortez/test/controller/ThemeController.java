package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kortez.test.domain.Theme;
import ru.kortez.test.domain.User;
import ru.kortez.test.service.ThemeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ThemeController {
    @Autowired
    private ThemeService themeService;


    //show list of themes
    @GetMapping("/themes")
    String themesList(Model model){
        List themes = themeService.getAllThemes();
        model.addAttribute("themes", themes);
        return "themes";
    }

    //add new theme
    @PostMapping("/themes")
    String addTheme(@AuthenticationPrincipal User user,
                    @Valid Theme theme,
                    BindingResult bindingResult,
                    Model model){
        if (bindingResult.hasErrors())
        {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("theme", theme);
        }
        else
        {
            themeService.addNewTheme(theme, user);
            model.addAttribute("theme", null);
        }
        model.addAttribute("themes", themeService.getAllThemes());
        return "/themes";
    }

    //delete theme
    @PostMapping("/delTheme")
    String deleteTheme(@RequestParam("theme_id") Theme theme){
        themeService.deleteTheme(theme);
        return "redirect:/themes";
    }
    //show messages which belong theme
    @GetMapping("/theme/{theme}")
    String showTheme(@PathVariable Theme theme,
                     Model model){
        List messages = themeService.getGetAllMessageFromTheme(theme);
        model.addAttribute("messages", messages);
        model.addAttribute("theme_id", theme.getId());
        return "messages";
    }
}
