package ru.kortez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        Iterable<Message> messages = messageRepository.findAllByTheme(theme);
        model.addAttribute("messages", messages);
        model.addAttribute("theme_id", theme.getId());
        return "messages";
    }
}
