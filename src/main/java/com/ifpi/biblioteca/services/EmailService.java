/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.ifpi.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email){
        var message = new SimpleMailMessage();
        message.setFrom("noreply@email.com")
        message.setText(email.body());
        message.setTo(email.to());
        message.setSubject(email.subject());
        javaMailSender.send(message);
    }

}
