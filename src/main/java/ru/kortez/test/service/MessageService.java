package ru.kortez.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kortez.test.domain.Message;
import ru.kortez.test.domain.Theme;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.MessageRepository;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public boolean addMessageToTheme(Message message, Theme theme, User user){
        if (message != null && theme != null){
            message.setTheme(theme);
            message.setAuthor(user);
            message.setDate(new Date());
            messageRepository.save(message);
            return true;
        }
        else
            return false;
    }

    public boolean deleteMessageFromTheme(Message message){
        if(message != null){
            messageRepository.delete(message);
            return true;
        }
        else
            return false;
    }

    public List<Message> getAllMessageTheme(Theme theme) {
        if(theme != null){
            return messageRepository.findAllByTheme(theme);
        }
        else
            return null;
    }

}
