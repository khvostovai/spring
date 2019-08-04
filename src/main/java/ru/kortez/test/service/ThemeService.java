package ru.kortez.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kortez.test.domain.Theme;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.ThemeRepository;

import java.util.Date;
import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private MessageService messageService;

    public boolean addNewTheme(Theme theme, User user) {
        if(theme != null && user!=null) {
            theme.setAuthor(user);
            theme.setDate(new Date());
            themeRepository.save(theme);
            return true;
        }
        else
            return false;
    }

    public List getAllThemes() {
        return (List) themeRepository.findAll();
    }

    public boolean deleteTheme(Theme theme) {
        if(theme != null)
        {
            messageService.deleteAllMessageFromTheme(theme);
            themeRepository.delete(theme);
            return true;
        }
        else
            return false;
    }

    public List getGetAllMessageFromTheme(Theme theme) {
        if (theme != null) {
            return messageService.getAllMessageTheme(theme);
        }
        else
            return null;
    }
}
