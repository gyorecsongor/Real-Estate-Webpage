package com.ingatlan2.ingatlan.controller;

import com.ingatlan2.ingatlan.entities.Advert;
import com.ingatlan2.ingatlan.entities.User;
import com.ingatlan2.ingatlan.repository.UserRepository;
import com.ingatlan2.ingatlan.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FavoritesController {
    private AdvertService advertService;
    private UserRepository userRepository;
    @Autowired
    public void setAdvertService(UserRepository userRepository,AdvertService advertService) {
        this.userRepository = userRepository;
        this.advertService = advertService;
    }



    @RequestMapping("/favorites")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedin= userRepository.findByFullname(currentPrincipalName);
        model.addAttribute("adverts",advertService.listAdvertfavs(loggedin.getUserid()));
        System.out.println( "edvencek"+advertService.listAdvertfavs(loggedin.getUserid()));
        return "favorites";
    }
}
