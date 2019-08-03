package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kortez.test.domain.Message;
import ru.kortez.test.domain.Theme;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.MessageRepository;
import ru.kortez.test.service.MessageService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    //add message to theme
    @PostMapping("/addMessage")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam("theme_id") Theme theme,
                             @Valid Message message,
                             BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("message", message);
            model.addAttribute("messages", messageService.getAllMessageTheme(theme));
            model.addAttribute("theme_id",theme.getId());
            return "messages";
        }
        else {
            messageService.addMessageToTheme(message, theme, user);
            model.addAttribute("message", null);
        }
        return "redirect:/theme/"+theme.getId();
    }

    //delete message from theme
    @PostMapping("/delMessage")
    public String delMessage(@RequestParam("message_id") Message message,
                             @RequestParam("theme_id") Theme theme,
                             Model model){
        if(message != null)
            messageRepository.delete(message);
        if(theme != null)
        {
            model.addAttribute("theme_id", theme.getId());
            model.addAttribute("messages", messageRepository.findAllByTheme(theme));
            return "redirect:/theme/" + theme.getId();
        }
        return "redirect:/themes";
    }
}
