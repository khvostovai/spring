package ru.kortez.test.controller;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.kortez.test.domain.User;
import ru.kortez.test.domain.dto.CapchaResposeDto;
import ru.kortez.test.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private String RECAPCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${capcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String passwordConfirm,
                          @RequestParam("g-recaptcha-response") String respose,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model){
        boolean isConfirmEmpty = passwordConfirm.isEmpty();
        if(isConfirmEmpty)
            model.addAttribute("password2Error","Password confirmation can't be empty");

        String url = String.format(RECAPCHA_URL, secret, respose);
        CapchaResposeDto resposeDto = restTemplate.postForObject(url, Collections.EMPTY_LIST, CapchaResposeDto.class);

        if(!resposeDto.isSuccess())
            model.addAttribute("capchaError", "Enter the capcha");

        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirm) ){
            model.addAttribute("passwordcleanError", "Passwords are different");
        }
        if(bindingResult.hasErrors() || isConfirmEmpty || !resposeDto.isSuccess()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "/registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("usernameError", "user exist");
            return "registration";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/activate/{code}")
    public String actvate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        }
        else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}
