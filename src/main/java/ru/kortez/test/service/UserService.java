package ru.kortez.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.kortez.test.domain.Role;
import ru.kortez.test.domain.User;
import ru.kortez.test.repos.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            return false;
        }
        user.setActive(true);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        if(!StringUtils.isEmpty(user.getEmail()))
        {
            sendMessage(user);
        }
        return true;
    }

    private void sendMessage(User user) {
        String message = String.format("" +
                "Hello, %s \n" +
                "For activation your account on Forum click here: http://localhost:8080/activate/%s",
                user.getUsername(), user.getActivationCode());
        mailSender.send(user.getEmail(), "Activation code", message);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        else {
            user.setActivationCode(null);
            userRepository.save(user);
            return true;
        }
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void updateProfile(User user, String email, String password) {
        String userEmail = user.getEmail();

        boolean isEmailChange = ((email != null && !email.equals(userEmail)) ||
                (userEmail != null && userEmail.equals(email)));

        if(isEmailChange){
            user.setEmail(email);
        }

        if(!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }


        if(!StringUtils.isEmpty(user.getEmail())){
            user.setActivationCode(UUID.randomUUID().toString());
            sendMessage(user);
        }

        userRepository.save(user);
    }
}
