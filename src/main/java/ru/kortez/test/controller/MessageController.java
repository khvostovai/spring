package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kortez.test.domain.Message;
import ru.kortez.test.domain.Theme;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.MessageRepository;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    //add message to theme
    @PostMapping("/addMessage")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam("theme_id") Theme theme,
                             @RequestParam String message,
                             @RequestParam String tag,
                             Model model){
        if (theme != null && message != null && !message.equals("") )
            messageRepository.save(new Message(message, tag, user, theme));
        model.addAttribute("messages", messageRepository.findAllByTheme(theme));
        model.addAttribute("theme_id", theme.getId());
        return "redirect:/theme/" + theme.getId();
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
