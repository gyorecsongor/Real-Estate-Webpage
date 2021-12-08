package com.ingatlan2.ingatlan.controller;

import com.ingatlan2.ingatlan.entities.User;
import com.ingatlan2.ingatlan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ingatlan2.ingatlan.service.EmailService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private EmailService emailService;

    private UserService userService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/reg")
    public String greetingSubmit(@ModelAttribute User user){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        System.out.println("UJ USER:");
        log.debug(user.getFullname());
        log.debug(user.getEmail());
        log.debug(user.getPassword());
        userService.registerUser(user);
        emailService.sendMessage(user.getEmail(),user.getFullname(),user.getActivation());
        return "login";
    }

    @RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
    public String activation(@PathVariable("code") String code, HttpServletResponse response) {
        String result = userService.userActivation(code);
        return "login";
    }
}

