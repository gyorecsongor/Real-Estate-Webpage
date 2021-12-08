package com.ingatlan2.ingatlan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @RequestMapping("/account")
    public String home(){
        return "account";
    }
}
