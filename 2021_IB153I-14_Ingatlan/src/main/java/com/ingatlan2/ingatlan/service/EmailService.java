package com.ingatlan2.ingatlan.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final Log log = (Log) LogFactory.getLog(this.getClass());

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String fullname, String activation){
        SimpleMailMessage message = null;

        try{
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(email);
            message.setSubject("Sikeres Regisztráció!");
            message.setText("Kedves " + fullname + "! \n \n Köszönjük, hogy regisztált! \n \n Kattintson a következő linkre az aktiváláshoz:\n"+"https://teszt-2021-ib153i-14-ingatlan.herokuapp.com/activation/"+activation
                    +"\n \n Üdvözlettel az Ingatlan2.com csapata!");
            javaMailSender.send(message);
        }
        catch (Exception e){
            log.error("Hiba az e-mail küldésekor az alábbi címre:" + email + " " + e);
        }
    }

    public void sendMessage(String email, String fullname) {
        SimpleMailMessage message = null;

        try{
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(email);
            message.setSubject("Változás a Kedvelésekben");
            message.setText("Kedves " + fullname + "! \n \n Kitöröltek egy elemet a kedvelt hirdetései közül! \n "
                    +"\n \n Üdvözlettel az Ingatlan2.com csapata!");

            javaMailSender.send(message);
        }
        catch (Exception e){
            log.error("Hiba az e-mail küldésekor az alábbi címre:" + email + " " + e);
        }

    }
}


