package com.ingatlan2.ingatlan.controller;

import com.ingatlan2.ingatlan.entities.Advert;
import com.ingatlan2.ingatlan.entities.User;
import com.ingatlan2.ingatlan.service.AdvertService;
import com.ingatlan2.ingatlan.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private AdvertService advertService;

    @Autowired
    public void setAdvertService(AdvertService advertService) {
        this.advertService = advertService;
    }


    @GetMapping(value = "/")
    public String listAdvert(Model model){
        return home(model);
    }

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("adverts",advertService.listAdverts());
        return "index";
    }
}
