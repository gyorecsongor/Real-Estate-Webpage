package com.ingatlan2.ingatlan.controller;
import com.ingatlan2.ingatlan.service.EmailService;
import com.ingatlan2.ingatlan.entities.Advert;
import com.ingatlan2.ingatlan.entities.User;
import com.ingatlan2.ingatlan.repository.UserRepository;
import com.ingatlan2.ingatlan.service.AdvertService;
import com.ingatlan2.ingatlan.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HirdetesController {

    private AdvertService advertService;
    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setAdvertService(UserRepository userRepository,AdvertService advertService) {
        this.userRepository = userRepository;
        this.advertService = advertService;
    }

    private UserServiceImpl userServiceImpl;
    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl=userServiceImpl;
    }

    @RequestMapping("/hirdetes")
    public String home(Model model, @RequestParam(required = true) String id) {
        Advert advert = advertService.getAdvertById(Long.parseLong(id));

        if (advert == null) {
            return "detailedError";
        }

        model.addAttribute("id", advert.getAdvert_id());
        model.addAttribute("title", advert.getTitle());
        model.addAttribute("description", advert.getDescription());
        model.addAttribute("tags", advert.getTags());
        model.addAttribute("type", advert.getType());
        model.addAttribute("area", advert.getArea());
        model.addAttribute("roomCount", advert.getRoomCount());
        model.addAttribute("location", advert.getLocation());
        model.addAttribute("furnished", advert.isFurnished());
        model.addAttribute("useremail", advert.getUser().getEmail());

        return "hirdetes";
    }
    @PostMapping(value = "/add")
    public String addAdvert(Model model, @ModelAttribute User user, @ModelAttribute Advert advert){
        System.out.println("addAdvert" + user.getEmail() + advert.getLocation());
//        advert.setUser(user);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedin= userRepository.findByFullname(currentPrincipalName);



        advert.setUser(loggedin);
        advertService.insertAdvert(advert);
        model.addAttribute("adverts",advertService.listAdverts());
        return "index";
    }

    @GetMapping(value = "/new")
    public String newaAdvert(Model model, @ModelAttribute User user){
        System.out.println("newAdvert" + user.getEmail());
        model.addAttribute("advert", new Advert());
        return "new_hirdetes";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteAdvert(@PathVariable("id") long real_estate_id, Model model){
        List<Long> users = advertService.getFavourites(real_estate_id);
        for(Long userId: users) {
            User actUser = userRepository.findByUserid(userId);
            emailService.sendMessage(actUser.getEmail(),actUser.getFullname());
        }
        advertService.deleteFavourites(real_estate_id);
        advertService.deleteAdvert(real_estate_id);
        return "redirect:/index";
    }

    @GetMapping(value = "/edit")
    public String adverEditPage(Model model, @RequestParam(required = true) String id){
        Advert advert = advertService.getAdvertById(Long.parseLong(id));

        if (advert == null) {
            return "detailedError";
        }

        model.addAttribute("advert", advert);
        model.addAttribute("title", advert.getTitle());
        model.addAttribute("description", advert.getDescription());
        model.addAttribute("tags", advert.getTags());
        model.addAttribute("type", advert.getType());
        model.addAttribute("area", advert.getArea());
        model.addAttribute("roomCount", advert.getRoomCount());
        model.addAttribute("location", advert.getLocation());
        model.addAttribute("furnished", advert.isFurnished());

        advertService.deleteFavourites(Long.parseLong(id));
        advertService.deleteAdvert(Long.parseLong(id));

        return "edit_hirdetes";
    }

    @PostMapping(value = "/edit")
    public String editAdvert( Model model, @ModelAttribute User user, @ModelAttribute Advert advert){
        System.out.println("addAdvert" + user.getEmail() + advert.getLocation());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedin= userRepository.findByFullname(currentPrincipalName);

        advert.setUser(loggedin);
        advertService.insertAdvert(advert);
        model.addAttribute("adverts",advertService.listAdverts());
        return "redirect:/index";
    }

    @PostMapping(value = "/addFavourite")
    public String addFavourites(Model model,  @ModelAttribute Advert advert){

        System.out.println("advert.getAdvert_id() : " + advert.getAdvert_id());
        System.out.println("advert.getAdvert_id() desc: " + advert.getDescription());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        advertService.insertFavourite(4L, advert.getAdvert_id());

        return "favorites";
    }
    //    @PostMapping(value = "/addFavourite/{id}")
//    public String addFavourites(  @PathVariable("id") Long real_estate_id)
//    {
//        System.out.println("real_estate_id" + real_estate_id);
//        advertService.insertFavourite(4L, real_estate_id);
//        return "favourites";
//    }
    @GetMapping("/addFavourite/{id}")
    public String addFavourites(@PathVariable("id") long real_estate_id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        User user= userRepository.findByFullname(currentPrincipalName);
        System.out.println("userrepobol adatok"+user.getFullname()+user.getUserid()+user);
        advertService.insertFavourite(user.getUserid(), real_estate_id);
        return "redirect:/favorites";
    }
    @GetMapping(value = "/deletefav/{id}")
    public String deletefav(@PathVariable("id") long real_estate_id, Model model){

        advertService.deleteFavourites(real_estate_id);

        return "redirect:/favorites";
    }
}
