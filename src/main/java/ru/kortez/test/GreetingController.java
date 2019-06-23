package ru.kortez.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kortez.test.domain.Message;
import ru.kortez.test.repos.MessageRepository;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String showMessage(Model model){
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "index";
    }
    @PostMapping
    public String addMessage(@RequestParam String message, @RequestParam String tag, Model model){
        if (message != null && !message.equals("") )
            messageRepository.save(new Message(message, tag));
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "index";
    }
}
